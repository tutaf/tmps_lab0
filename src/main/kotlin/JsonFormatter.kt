class JsonFormatter : ListFormatter {
    override fun format(items: List<String>): String {
        return items.joinToString(
            prefix = "[",
            separator = ",",
            postfix = "]"
        ) { "\"$it\"" }
    }
}
