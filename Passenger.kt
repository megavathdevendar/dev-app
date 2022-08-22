import java.sql.DriverManager
//model class
data class Data(val passenger_id:Int,val passenger_name: String,val passenger_age:String,val gender:String,val phone:String)

fun main(args:Array<String>){
    println("hello")
    val jdbcUrl = "jdbc:mysql://localhost:3306/demoDBPassenger"
    val connection = DriverManager.getConnection(jdbcUrl,"root","Devendar@1122")
    println(connection.isValid(0))
//insert into data base

    val insert_res = connection.createStatement().executeUpdate("insert into passenger(passenger_name,passenger_age,gender,phone) values('Kamali',40,'F','9632587412')")

    if(insert_res > 0) {
        println("successfuly inserted record into passenger db !!! ")
    }else{
        println("insert not sucessful")
    }

    //update records
    val update_res = connection.createStatement().executeUpdate("update passenger set passenger_name='aaaabb' where passenger_id=17 ")
    if(update_res>0)
    {
        println("record succesfully updated")
    }
    else{
        println("insert not update")
    }

    //delete query records
    val delete_res = connection.createStatement().executeUpdate("delete from passenger where passenger_id =11 ")
    if(delete_res>0)
    {
        println("record succesfully deleted")
    }
    else{
        println("insert not deleted")
    }



//fetch the qurey from database
    val query = connection.prepareStatement("select * from passenger")
    val result = query.executeQuery()
    val user = mutableListOf<Data>()

    while (result.next()){
        val passenger_id = result.getInt("passenger_id")
        val passenger_name = result.getString("passenger_name")
        val passenger_age:String = result.getString("passenger_age")
        val gender:String = result.getString("gender")
        val phone:String = result.getString("phone")
        user.add(Data(passenger_id,passenger_name, passenger_age,gender,phone))
    }
    println(user)
}


