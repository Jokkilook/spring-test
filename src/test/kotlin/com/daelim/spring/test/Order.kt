data class Order (
    val products: List<Product>,
    val isDelivered: Boolean
)

data class Product(
    var name: String,
    val price : Int
)