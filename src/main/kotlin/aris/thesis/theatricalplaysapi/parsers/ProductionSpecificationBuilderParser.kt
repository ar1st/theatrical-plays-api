package aris.thesis.theatricalplaysapi.parsers

import aris.thesis.theatricalplaysapi.specifications.builder.PersonSpecificationBuilder
import aris.thesis.theatricalplaysapi.specifications.builder.ProductionSpecificationBuilder
import java.util.regex.Matcher
import java.util.regex.Pattern

class ProductionSpecificationBuilderParser: Parser<ProductionSpecificationBuilder> {

    override fun parse(query: String): ProductionSpecificationBuilder {
        val builder = ProductionSpecificationBuilder()
        val pattern: Pattern = Pattern.compile("([\\w ]*[^\\W_][\\w ]*?)([:<>!~])([\\w ]*[^\\W_][\\w ]*?),", Pattern.UNICODE_CHARACTER_CLASS)
        val matcher: Matcher = pattern.matcher("$query,")
        while (matcher.find()) {
            val g1 = matcher.group(1)
            val g2 = matcher.group(2)
            val g3 = matcher.group(3)

            builder.with(g1,g2,g3)
        }

        return builder
    }
}