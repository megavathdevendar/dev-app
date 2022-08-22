import java.sql.DriverManager
//model class
data class UserData(val train_id:Int,val train_no: String,val train_name:String,val source:String,val destination:String,val start_date_time:String,val arrival_time:String)

fun main(args:Array<String>){
    //println("hello")
    val jdbcUrl = "jdbc:mysql://localhost:3306/demoDBPassenger"
    val connection = DriverManager.getConnection(jdbcUrl,"root","Devendar@1122")
    println(connection.isValid(0))
//insert into data base

    val insert_res = connection.createStatement().executeUpdate("insert into Train(train_no,train_name,source,destination,start_date_time,arrival_time) values(1598,'csk','pune','delhi','01/01/1900 6:00AM','02/25/2022 5:00PM')")


    if(insert_res > 0) {
        println("successfuly inserted record into Train db !!! ")
    }else{
        println("insert not sucessful")
    }

    //update records
    val update_res = connection.createStatement().executeUpdate("update Train set train_name='kkr' where train_id=05 ")
    if(update_res>0)
    {
        println("record succesfully updated")
    }
    else{
        println("insert not update")
    }

    //delete query records
    val delete_res = connection.createStatement().executeUpdate("delete from Train where train_id =1 ")
    if(delete_res>0)
    {
        println("record succesfully deleted")
    }
    else{
        println("insert not deleted")
    }



//fetch the qurey from database
    val query = connection.prepareStatement("select * from Train")
    val result = query.executeQuery()
    val user = mutableListOf<UserData>()

    while (result.next()){
        val train_id = result.getInt("train_id")
        val train_no = result.getString("train_no")
        val train_name:String = result.getString("train_name")
        val source:String = result.getString("source")
        val destination:String = result.getString("destination")
        val start_date_time:String = result.getString("start_date_time")
        val arrival_time:String = result.getString("arrival_time")
        user.add(UserData(train_id,train_no, train_name,source, destination, start_date_time, arrival_time))
    }
    println(user)
}


