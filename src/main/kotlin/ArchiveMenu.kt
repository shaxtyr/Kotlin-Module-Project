//В конструкторе класса задается ссылка на меню заметок, что переходить в него из меню архивов

class ArchiveMenu(val archives: MutableList<Archive>, val noteMenu: NoteMenu) : Menu<Archive>("Меню архивов") {

    var nameOfAarchive: String = ""
    var outFromNoteMenu: Boolean = false        //флаг для выхода из меню заметок

    override fun setup() {

        addItem(MenuItem("Создать архив") {
            createItem()
        })

        addItem(MenuItem("Показать текущие архивы") {
            if (listItems().isNotEmpty()) {
                println("")
                println("Список созданных архивов: ")
                listItems().forEachIndexed {index, archive ->
                    println("$index. ${archive.name}")
                }
                while (true) {
                    println("(-_-)")
                    print("Выберете архив для просмотра: ")
                    when(val choice = readLine()?.toIntOrNull()) {
                        null -> println("Вам нужно ввести цифру. Попробуйте еще раз")
                        !in listItems().indices -> println("Такой цифры нет. Попробуйте еще раз")
                        else -> {
                            openItem(listItems()[choice])
                            break
                        }
                    }
                }
            } else {
                println("Список архивов пуст")
            }
        })

        addItem(MenuItem("Выход") {
            out = true
        })
    }

    override fun createItem() {
        while (true) {
            println("(-_-)")
            print("Введите имя для нового архива: ")
            nameOfAarchive = readlnOrNull().toString()
            if (nameOfAarchive.isEmpty()) {
                println("Имя архива не должно быть пустым. Попробуйте еще раз")
            } else  break
        }
        archives.add(Archive(nameOfAarchive, null))
        showMenu()
    }

    override fun listItems(): List<Archive> = archives

    override fun openItem(item: Archive) {
        while (!outFromNoteMenu) {
            noteMenu.showMenu()
            outFromNoteMenu = noteMenu.goToArchiveMenu
        }
        outFromNoteMenu = false

    }

}


