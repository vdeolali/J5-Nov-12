package salesdept;
public class CustomerNameBean
{
    private String firstName;
    private String lastName;
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public void setFirstName( String firstName ) { this.firstName = firstName; }
    public void setLastName( String lastName ) { this.lastName = lastName; }
    public String getName()
    { 
        return firstName + " " + lastName;
    }
}