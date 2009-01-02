/*
 * Copyright 2005-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.dozer.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.List;

import net.sf.dozer.AbstractDozerTest;
import net.sf.dozer.classmap.ClassMap;
import net.sf.dozer.classmap.MappingFileData;
import net.sf.dozer.fieldmap.FieldMap;

import org.junit.Test;

/**
 * @author garsombke.franz
 * @author johnsen.knut-erik
 */
public class XMLParserTest extends AbstractDozerTest {

  private XMLParser parser = new XMLParser();

  @Test
  public void testParse() throws Exception {
    ResourceLoader loader = new ResourceLoader();
    URL url = loader.getResource("dozerBeanMapping.xml");

    MappingFileData mappings = parser.parse(url.openStream());
    assertNotNull(mappings);
  }

  /**
   * This tests checks that the customconverterparam reaches the
   * fieldmapping.      
   */
  @Test
  public void testParseCustomConverterParam() throws Exception {
    ResourceLoader loader = new ResourceLoader();
    URL url = loader.getResource("fieldCustomConverterParam.xml");

    MappingFileData mappings = parser.parse(url.openStream());

    assertNotNull("The mappings should not be null", mappings);

    List<ClassMap> mapping = mappings.getClassMaps();

    assertNotNull("The list of mappings should not be null", mapping);

    assertEquals("There should be one mapping", 3, mapping.size());

    ClassMap classMap = mapping.get(0);

    assertNotNull("The classmap should not be null", classMap);

    List<FieldMap> fieldMaps = classMap.getFieldMaps();

    assertNotNull("The fieldmaps should not be null", fieldMaps);
    assertEquals("The fieldmap should have one mapping", 1, fieldMaps.size());

    FieldMap fieldMap = fieldMaps.get(0);

    assertNotNull("The fieldmap should not be null", fieldMap);
    assertEquals("The customconverterparam should be correct", "CustomConverterParamTest", fieldMap.getCustomConverterParam());
  }

  @Test
  public void testGetFieldNameOfIndexedField() {
    assertEquals("aaa", parser.getFieldNameOfIndexedField("aaa[0]"));
    assertEquals("aaa[0].bbb", parser.getFieldNameOfIndexedField("aaa[0].bbb[1]"));
    assertEquals("aaa[0].bbb[1].ccc", parser.getFieldNameOfIndexedField("aaa[0].bbb[1].ccc[2]"));
  }

}
