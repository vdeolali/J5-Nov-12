// ------------ Begin: CustomerDBProcess.java ----------------------
package salesdept;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
public class CustomerDBProcess
{
    private ResultSet rs;
    private Statement statement;
    // constructor
    public CustomerDBProcess() throws ClassNotFoundException,
							InstantiationException,
							IllegalAccessException,
							SQLException
    {
	String username="root";
	String password="sesame";
	String dbURL="jdbc:mysql://localhost:3306/orderdatabase";
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection connection = DriverManager.getConnection(dbURL, username, password);
	statement = connection.createStatement();
	rs = null;
    }
    // Get all customers' data
    public List<CustomerDBObject> getCustomerList() throws SQLException
    {
	List<CustomerDBObject> custList = null;
	rs = statement.executeQuery("select * from customer");
	String rowClass = "tableEvenRow";
	while (rs.next())
	{
	    CustomerDBObject cust = new CustomerDBObject();
	    cust.setCustomerId (rs.getInt("customer_id"));
	    cust.setAge(rs.getInt("age"));
	    cust.setOrderValue(rs.getDouble("order_value"));
	    cust.setFirstName(rs.getString("first_name"));
	    cust.setLastName(rs.getString("last_name"));
	     // Toggle the CSS style sheet class
	if (rowClass.equals("tableEvenRow"))
	{
	    rowClass = "tableOddRow";
	}
	else
	{
	    rowClass = "tableEvenRow";
	}
	cust.setRowClass(rowClass);

	    if (custList == null) custList = new ArrayList<CustomerDBObject>();
	    custList.add(cust);
	}
	return custList;
    }
    public String getMaxCustomerId() throws SQLException {
	int cust_id = 0;
	System.out.println("Vikas: " + cust_id);

	rs = statement.executeQuery("select MAX(customer_id) FROM customer ");
	while(rs.next()){
	    cust_id = rs.getInt("MAX(customer_id)");
	}

	System.err.println("Vikas: " + cust_id);
	return (Integer.toString(cust_id));
    }

    public void addCustomer(CustomerDBObject cust) throws SQLException{
	int cust_id = cust.getCustomerId();
	String f_name = cust.getFirstName();
	String l_name = cust.getLastName();
	int age = cust.getAge();
	double order_val = cust.getOrderValue();

	System.out.println ("CustDBPro:AddCust  " + f_name + cust_id);
	statement.executeUpdate("INSERT INTO customer(customer_id, first_name, last_name, age, order_value) value("+cust_id+", '"+f_name+"', '"+l_name+"', "+age+", "+order_val+")");
    }
}
// ------------ End: CustomerDBProcess.java ----------------------
