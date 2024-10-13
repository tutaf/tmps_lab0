import kotlin.random.Random

fun main() {
    val items = listOf("Some item 1", "A different item 2", "Yet another item 3")

    // we'll pick a random formatter
    val formatters: List<ListFormatter> = listOf(
        PlainTextFormatter(),
        JsonFormatter(),
        HtmlFormatter()
    )

    val randomFormatter = formatters[Random.nextInt(formatters.size)]

    // printing list using the selected formatter
    val listPrinter = ListPrinter(randomFormatter)
    listPrinter.printList(items)
}
