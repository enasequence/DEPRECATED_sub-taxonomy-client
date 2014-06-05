package uk.ac.ebi.ena.taxonomy.client.model;

import org.junit.Test;
import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaxonomyReponseTest {

  @Test
  public void testBean() {
      assertThat(TaxonomyResponse.class, allOf(
              hasValidBeanConstructor(),
              hasValidGettersAndSetters(),
              hasValidBeanHashCode(),
              hasValidBeanEquals(),
              hasValidBeanToString()
      ));
  }
}
