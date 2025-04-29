package br.com.lucascordeiro.nexly.feature.home.presentation.model

data class SortOption(
    val label: String,
    val isSelected: Boolean,
    val field: Field
) {
    constructor(field: Field) : this(
        label = field.name,
        isSelected = false,
        field = field
    )

    enum class Field {
        ID,
        Name,
        Volume
    }
}