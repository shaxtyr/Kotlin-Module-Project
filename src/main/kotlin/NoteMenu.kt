class NoteMenu(val notes: MutableList<Note>) : Menu<Note>("Меню заметок") {

    var nameOfNote = ""
    var infoOfNote = ""
    var goToArchiveMenu: Boolean = false                //флаг для обратного прыжка в меню архивов

    override fun setup() {

        addItem(MenuItem("Создать заметку") {
            createItem()
        })

        addItem(MenuItem("Показать текущие заметки") {
            if (listItems().isNotEmpty()) {
                println("")
                println("Список созданных заметок: ")
                listItems().forEachIndexed {index, note ->
                    println("$index. ${note.name}")
                }
                while (true) {
                    println("(-_-)")
                    print("Выберете заметку для просмотра: ")
                    when(val choice = readLine()?.toIntOrNull()) {
                        null -> println("Вам нужно ввести цифру. Попробуйте еще раз")
                        !in listItems().indices -> println("Такой цифры нет. Попробуйте еще раз")
                        else -> {
                            print("Текст выбранной заметки: ")
                            openItem(listItems()[choice])
                            break
                        }
                    }
                }
            } else {
                println("Список заметок пуст")
            }
        })

        addItem(MenuItem("Выход") {
            goToArchiveMenu = true
        })
    }

    override fun createItem() {
        while (true) {
            println("(-_-)")
            print("Введите имя для новой заметки: ")
            nameOfNote = readlnOrNull().toString()
            if (nameOfNote.isEmpty()) {
                println("Имя заметки не должно быть пустым. Попробуйте еще раз")
            } else  break
        }
        while (true) {
            print("Введите содержимое для новой заметки: ")
            infoOfNote = readlnOrNull().toString()
            if (infoOfNote.isEmpty()) {
                println("Содержимое заметки не должно быть пустым. Попробуйте еще раз")
            } else  break
        }

        notes.add(Note(nameOfNote, infoOfNote))
        goToArchiveMenu = false
    }

    override fun listItems(): List<Note> = notes

    override fun openItem(item: Note) {
        println(item.info)
        goToArchiveMenu = false
    }

}


