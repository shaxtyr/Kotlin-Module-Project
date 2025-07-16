fun main() {

    val noteMenu = NoteMenu(mutableListOf<Note>())
    val archiveMenu = ArchiveMenu(mutableListOf<Archive>(), noteMenu)

    noteMenu.setup()
    archiveMenu.setup()
    while (!archiveMenu.out) {
        archiveMenu.showMenu()
    }
}

