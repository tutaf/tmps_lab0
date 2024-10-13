class PlainTextFormatter : ListFormatter {
    override fun format(items: List<String>): String {
        return items.joinToString(separator = "\n")
    }
}
