class ListPrinter(private val formatter: ListFormatter) {
    fun printList(items: List<String>) {
        val formattedList = formatter.format(items)
        println("Formatted List:\n$formattedList")
    }
}
