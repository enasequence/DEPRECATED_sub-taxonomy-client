package uk.ac.ebi.ena.taxonomy.client.model;

public class TaxonomyRequest {

  public enum Task {match_name};
  
  private Task task;
  
  private String name;

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
}
