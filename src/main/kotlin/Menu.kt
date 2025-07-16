
data class MenuItem(val nameItem: String, val action: () -> Unit)

abstract class Menu<T>(val title: String) {
    val items = mutableListOf<MenuItem>()
    var out: Boolean = false

    fun addItem(item: MenuItem) {
        items.add(item)
    }

    //метод для отображения всего меню с проверкой на корректный ввод
    fun showMenu() {
        while (true) {
            println()
            println("--$title--")
            items.forEachIndexed { index, item ->
                println("$index. ${item.nameItem}")
            }
            print("Выберете пункт: ")
            when (val input = readLine()?.toIntOrNull()) {
                null -> println("Вам нужно ввести цифру. Попробуйте еще раз")
                !in items.indices -> println("Такой цифры нет. Попробуйте еще раз")
                else -> {
                    items[input].action()
                    break
                }
            }
        }
    }

    abstract fun setup()                    //метод для создания полей для каждого из меню
    abstract fun createItem()               //метод для создания новых архивов/заметок
    abstract fun openItem(item: T)          //метод для открытия архивов/заметок
    abstract fun listItems(): List<T>       //метод для отображения полей меню
}

