import java.util.Set;

public class Person {

  private String name;
  private int age;
  private Set<Person> confidants;

  public Person(String name, int age, Set<Person> confidants) {
    this.name = name;
    this.age = age;
    this.confidants = confidants;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Set<Person> getConfidants() {
    return confidants;
  }
}