package by.serzh

class CsvData {
    var heading = ArrayList<String>()


    val data = ArrayList<List<String>>()

    fun setHeading(value: List<String>) {
        heading.clear()
        heading.addAll(value)
    }

    fun addLine(lineItems: java.util.ArrayList<String>) {
        data.add(lineItems)
    }
}