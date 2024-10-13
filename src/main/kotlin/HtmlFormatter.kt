class HtmlFormatter : ListFormatter {
    override fun format(items: List<String>): String {
        return "<ul>\n" + items.joinToString(separator = "\n") { "<li>$it</li>" } + "\n</ul>"
    }
}
