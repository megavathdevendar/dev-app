import java.sql.DriverManager
data class Ticket(val id:Int,val ticket_number:Int,val passenger_id:Int,val train_id:Int, val ticket_price:Int)

fun main(args: Array<String>) {
    val jdbc = "jdbc:mysql://localhost:3306/demoDBPassenger"
    val connection = DriverManager.getConnection(jdbc, "root", "Devendar@1122")
    println(connection.isValid(0))

    //Insert query
    val res_insert = connection.createStatement()
        .executeUpdate("Insert into Ticket(id,ticket_number,passenger_id,train_id,ticket_price)values(11,20199,13,3,968)")
    if (res_insert > 0) {
        println("Record inserted successfully")
    } else {
        println("Unable to insert record")
    }

    //Update query

    val res_update = connection.createStatement().executeUpdate("Update Ticket set ticket_price = 2200 where id = 2")
    if (res_insert > 0) {
        println("Record updated successfully")
    } else {
        println("Unable to update record")
    }
    //delete query

    val res_delete = connection.createStatement().executeUpdate("delete from Ticket where id=104")
    if (res_delete > 0) {
        println("Record deleted successfully")
    } else {
        println("Unable to delete record")
    }


    //select query
    val query = connection.prepareStatement("select * from Ticket")
    val result = query.executeQuery()
    val Ticket = mutableListOf<Ticket>()

    while (result.next()) {
        val id = result.getInt("id")
        val ticket_number = result.getInt("ticket_number")
        val passenger_id = result.getInt("passenger_id")
        val train_id = result.getInt("train_id")
        val ticket_price = result.getInt("ticket_price")
        Ticket.add(Ticket(id, ticket_number, passenger_id, train_id, ticket_price))
    }
    println(Ticket)
}