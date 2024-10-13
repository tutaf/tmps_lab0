# Lab 0 - Report
##### by Cernisov Andrei, FAF-222

This project converts lists of strings into different output formats - plain text, JSON, and HTML, while trying to adhere to and demonstrate the SOLID principles

## How It Works

The application consists of several classes and an interface:

1. **ListFormatter Interface**: Defines a contract for formatting a list of strings.
2. **Concrete Formatters**: Implementations of the `ListFormatter` interface that provide specific formatting strategies:
   - **PlainTextFormatter**: Formats the list as plain text with each item on a new line
   - **JsonFormatter**: Formats the list as a JSON array of strings.
   - **HtmlFormatter**: Formats the list as an unordered HTML list.
3. **ListPrinter Class**: Responsible for printing the formatted list using a given formatter
4. **Main Function**: Entry point of the application that randomly selects a formatter and uses the `ListPrinter` to display the formatted output.

### Code Structure

#### 1. ListFormatter Interface

```kotlin
interface ListFormatter {
    fun format(items: List<String>): String
}
```

The `ListFormatter` interafce defines a single method (`format`)  which takes a list of strings and returns a formatted string.

#### 2. Concrete Formatters

##### **PlainTextFormatter**

```kotlin
class PlainTextFormatter : ListFormatter {
    override fun format(items: List<String>): String {
        return items.joinToString(separator = "\n") 
    }
}
```

This formatter joins the list items using newline characters to create a plain text representation

##### **JsonFormatter**

```kotlin
class JsonFormatter : ListFormatter {
    override fun format(items: List<String>): String {
        return items.joinToString(
            prefix = "[",
            separator = ",",
            postfix = "]"
        ) { "\"$it\"" } 
    }
}
```

This formatter creates a JSON array by joining the items with commas and surrounding each item with double quotes.

##### **HtmlFormatter**

```kotlin
class HtmlFormatter : ListFormatter {
    override fun format(items: List<String>): String {
        return "<ul>\n" + items.joinToString(separator = "\n") { "<li>$it</li>" } + "\n</ul>"
    }
}
```

This formatter produces an HTML undorered list, wrapping each item in `<li>` tags

#### 3. ListPrinter Class

```kotlin
class ListPrinter(private val formatter: ListFormatter) {
    fun printList(items: List<String>) {
        val formattedList = formatter.format(items)
        println("Formatted List:\n$formattedList")
    }
}
```

The `ListPrinter` class takes a `ListFormatter` in its constructor and uses it to format and print the list of items

#### 4. Main Function

```kotlin
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
```

The `main` function initializes a list of items,  selects a random formatter from a predefined list, and uses the `ListPrinter` to display the formatted output

## Adherence to SOLID Principles

The project adheres to the following SOLID principles:

### 1. **Single Responsibility Principle (SRP)**

Each class in the project has a single responsibility:
- **ListFormatter Interface**: Defines the contract for formatting.
- **Concrete Formatters**: Each formater (plain text, JSON, HTML) is responsible for formatting the list in its specific way.
- **ListPrinter**: Responsible for printing the formatted list.

### 2. **Open/Closed Principle (OCP)**

The system is open for extension but closed for modification:
- New formatters can be added (for example, a `CsvFormatter`) without modifying the existing classes.  The `ListPrinter` can work with any class that implements the `ListFormatter` interface

### 3. **Liskov Substitution Principle (LSP)**

The project adheres to LSP because any instance of ListFormatter can be substituted with any of its subclasses (`PlainTextFormatter`, `JsonFormatter`, `HtmlFormatter`) without affecting the functionality of the `ListPrinter` class

### 4. **Interface Segregation Principle (ISP)**

The project adheres to ISP because ListFormatter interface is simple and focused on one function, which ensures that classes implementing it are not forced to implement unnecessary methods

### 5. **Dependency Inversion Principle (DIP)**

Project adheres to DIP because  ListPrinter class depends on the ListFormatter interface rather than concrete implementations. This allows for flexible swapping of formatters without modifying the ListPrinter class.
