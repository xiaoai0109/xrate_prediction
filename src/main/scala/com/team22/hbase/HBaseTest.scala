package com.team22.hbase

import java.io.IOException

import org.apache.hadoop.hbase.{HBaseConfiguration, HColumnDescriptor, HTableDescriptor, TableName}
import org.apache.hadoop.hbase.client._
import org.apache.hadoop.hbase.util.Bytes

object HBaseTest {
  val configuration = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(configuration)
  val admin = connection.getAdmin
  val table = connection.getTable(TableName.valueOf("sk:test1"))

  // Create a table
  def createTable(tableName: String, columnFamilys: Array[String]) = {
    val tName = TableName.valueOf(tableName)
    // Create a table if it doesn't exist
    if (!admin.tableExists(tName)) {
      val descriptor = new HTableDescriptor(tName)
      for (columnFamily <- columnFamilys) {
        descriptor.addFamily(new HColumnDescriptor(columnFamily))
      }
      admin.createTable(descriptor)
      println("create successful!!")
    }
  }

  // Insert to a table
  // put 'sk:test1','1','i:name','Luck2'
  def insertTable(rowkey: String, columnFamily: String, column: String, value: String) = {
    val puts = new Put(rowkey.getBytes())
    puts.addColumn(columnFamily.getBytes(), column.getBytes(), value.getBytes())
    table.put(puts)
    println("insert successful!!")
  }

  // Retrieve data from a table
  // scan 'sk:test1'
  def scanDataFromHTable(columnFamily: String, column: String) = {
    val scan = new Scan()
    scan.addFamily(columnFamily.getBytes())
    val scanner = table.getScanner(scan)
    var result = scanner.next()
    while (result != null) {
      println(s"rowkey:${Bytes.toString(result.getRow)},列簇:${columnFamily}:${column},value:${Bytes.toString(result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column)))}")
      result = scanner.next()
    }
    // Close Scanner
    scanner.close()
  }

  // Delete data from a table
  // delete 'sk:test1','1','i:name'
  def deleteRecord(rowkey: String, columnFamily: String, column: String) = {
    val info = new Delete(Bytes.toBytes(rowkey))
    info.addColumn(columnFamily.getBytes(), column.getBytes())
    table.delete(info)
    println("delete successful!!")
  }

  // Close connection
  def close()={
    if (connection!=null){
      try{
        connection.close()
        println("close success!")
      }catch{
        case e:IOException => println("close fail！")
      }
    }
  }

//  def main(args: Array[String]): Unit = {
//    //createTable("sk:test1",Array("i"))
//    //insertTable("1", "i", "age", "22")
//    //scanDataFromHTable("i", "age")
//    deleteRecord("1","i","name")
//    close()
//  }
}
