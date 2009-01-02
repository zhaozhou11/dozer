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
package net.sf.dozer.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.dozer.CustomFieldMapper;
import net.sf.dozer.DozerBeanMapper;
import net.sf.dozer.Mapper;
import net.sf.dozer.MappingException;
import net.sf.dozer.functional_tests.support.SampleDefaultBeanFactory;
import net.sf.dozer.functional_tests.support.TestCustomFieldMapper;
import net.sf.dozer.util.CollectionUtils;
import net.sf.dozer.vo.AnotherTestObject;
import net.sf.dozer.vo.AnotherTestObjectPrime;
import net.sf.dozer.vo.FieldValue;
import net.sf.dozer.vo.Fruit;
import net.sf.dozer.vo.InsideTestObject;
import net.sf.dozer.vo.MethodFieldTestObject;
import net.sf.dozer.vo.MethodFieldTestObject2;
import net.sf.dozer.vo.NoDefaultConstructor;
import net.sf.dozer.vo.NoReadMethod;
import net.sf.dozer.vo.NoReadMethodPrime;
import net.sf.dozer.vo.NoWriteMethod;
import net.sf.dozer.vo.NoWriteMethodPrime;
import net.sf.dozer.vo.PrimitiveArrayObj;
import net.sf.dozer.vo.PrimitiveArrayObjPrime;
import net.sf.dozer.vo.SimpleObj;
import net.sf.dozer.vo.SimpleObjPrime;
import net.sf.dozer.vo.TestObject;
import net.sf.dozer.vo.TestObjectPrime;
import net.sf.dozer.vo.TestObjectPrime2;
import net.sf.dozer.vo.allowedexceptions.TestException;
import net.sf.dozer.vo.allowedexceptions.ThrowException;
import net.sf.dozer.vo.allowedexceptions.ThrowExceptionPrime;
import net.sf.dozer.vo.context.ContextMapping;
import net.sf.dozer.vo.context.ContextMappingNested;
import net.sf.dozer.vo.context.ContextMappingNestedPrime;
import net.sf.dozer.vo.context.ContextMappingPrime;
import net.sf.dozer.vo.iface.ApplicationUser;
import net.sf.dozer.vo.iface.UpdateMember;
import net.sf.dozer.vo.index.Mccoy;
import net.sf.dozer.vo.index.MccoyPrime;
import net.sf.dozer.vo.isaccessible.Foo;
import net.sf.dozer.vo.isaccessible.FooPrime;
import net.sf.dozer.vo.isaccessible.PrivateConstructorBean;
import net.sf.dozer.vo.isaccessible.PrivateConstructorBeanPrime;
import net.sf.dozer.vo.orphan.Child;
import net.sf.dozer.vo.orphan.ChildPrime;
import net.sf.dozer.vo.orphan.Parent;
import net.sf.dozer.vo.orphan.ParentPrime;
import net.sf.dozer.vo.perf.MyClassA;
import net.sf.dozer.vo.perf.MyClassB;
import net.sf.dozer.vo.set.NamesArray;
import net.sf.dozer.vo.set.NamesSet;
import net.sf.dozer.vo.set.NamesSortedSet;
import net.sf.dozer.vo.set.SomeDTO;
import net.sf.dozer.vo.set.SomeOtherDTO;
import net.sf.dozer.vo.set.SomeVO;

import org.junit.Test;

/**
 * @author garsombke.franz
 */
public class GranularDozerBeanMapperTest extends AbstractFunctionalTest {

  @Test
  public void testNoDefaultConstructor() throws Exception {
    try {
      mapper.map("test", NoDefaultConstructor.class);
      fail("should have thrown exception");
    } catch (MappingException e) {
      assertEquals("java.lang.NoSuchMethodException: net.sf.dozer.vo.NoDefaultConstructor.<init>()", e.getMessage());
    }
  }

  @Test
  public void testFieldAccessible() throws Exception {
    Mapper mapper = getMapper(new String[] { "fieldAttributeMapping.xml" });
    TestObject to = newInstance(TestObject.class);
    to.setFieldAccessible("fieldAccessible");
    to.setFieldAccessiblePrimInt(2);
    InsideTestObject ito = newInstance(InsideTestObject.class);
    ito.setLabel("label");
    to.setFieldAccessibleComplexType(ito);
    String[] stringArray = new String[] { "one", "two" };
    to.setFieldAccessibleArrayToList(stringArray);
    TestObjectPrime top = mapper.map(to, TestObjectPrime.class);
    assertEquals("fieldAccessible", top.fieldAccessible);
    assertEquals("label", top.fieldAccessibleComplexType.getLabelPrime());
    assertEquals(2, top.fieldAccessiblePrimInt);
    assertEquals("one", top.fieldAccessibleArrayToList.get(0));
    assertEquals("two", top.fieldAccessibleArrayToList.get(1));

    // Map Back
    TestObject toDest = mapper.map(top, TestObject.class);
    assertEquals("fieldAccessible", toDest.getFieldAccessible());
    assertEquals("label", toDest.getFieldAccessibleComplexType().getLabel());
    assertEquals(2, toDest.getFieldAccessiblePrimInt());
    assertEquals("one", toDest.getFieldAccessibleArrayToList()[0]);
    assertEquals("two", toDest.getFieldAccessibleArrayToList()[1]);
  }

  @Test
  public void testOverloadGetSetMethods() throws Exception {
    Mapper mapper = getMapper(new String[] { "fieldAttributeMapping.xml" });
    TestObject to = newInstance(TestObject.class);
    Date date = new Date();
    to.setOverloadGetField(new Date());
    TestObjectPrime top = mapper.map(to, TestObjectPrime.class);
    assertEquals(date, top.getOverloadSetField());

    // Map Back
    TestObject toDest = (TestObject) mapper.map(top, TestObject.class);
    assertEquals(date, toDest.getOverloadGetField());
  }

  @Test
  public void testFieldCreateMethod() throws Exception {
    Mapper mapper = getMapper(new String[] { "fieldAttributeMapping.xml" });
    TestObject to = newInstance(TestObject.class);
    InsideTestObject ito = newInstance(InsideTestObject.class);
    // we did not set any values. this will be set in the 'createMethod'
    to.setCreateMethodType(ito);
    TestObjectPrime top = mapper.map(to, TestObjectPrime.class);
    assertEquals("myField", top.getCreateMethodType().getMyField());

    // Map Back
    TestObject toDest = mapper.map(top, TestObject.class);
    assertEquals("testCreateMethod", toDest.getCreateMethodType().getTestCreateMethod());
  }

  @Test
  public void testIntegerToString() throws Exception {
    Mapper mapper = getMapper(new String[] { "fieldAttributeMapping.xml" });
    TestObject to = newInstance(TestObject.class);
    Integer[] array = new Integer[] { new Integer(1), new Integer(2) };
    to.setArrayForLists(array);
    TestObjectPrime top = mapper.map(to, TestObjectPrime.class);
    assertEquals("1", top.getListForArray().get(0));
  }

  @Test
  public void testMapNull_MappingLevel() throws Exception {
    Mapper mapper = getMapper(new String[] { "nullFieldMapping.xml" });
    // check that null does not override an existing value when map-null="false"
    AnotherTestObject src = newInstance(AnotherTestObject.class);
    src.setField3(null);
    src.setField4(null);
    AnotherTestObjectPrime dest = newInstance(AnotherTestObjectPrime.class);
    dest.setTo(newInstance(TestObject.class));
    dest.setField3("555");
    dest.getTo().setOne("4641");

    // dest field should remain unchanged
    mapper.map(src, dest);
    assertEquals("invalid dest field value", "555", dest.getField3());
    assertEquals("invalid dest field value2", "4641", dest.getTo().getOne());
  }

  @Test
  public void testMapNull_MappingLevel2() throws Exception {
    Mapper mapper = getMapper(new String[] { "nullFieldMapping.xml" });
    // Reverse mapping
    AnotherTestObjectPrime src = newInstance(AnotherTestObjectPrime.class);
    src.setTo(newInstance(TestObject.class));
    src.setField3(null);
    src.getTo().setOne(null);
    AnotherTestObject dest = newInstance(AnotherTestObject.class);
    dest.setField3("555");
    dest.setField4("4641");

    // dest field should remain unchanged
    mapper.map(src, dest);
    assertEquals("invalid dest field value", "555", dest.getField3());
    assertEquals("invalid dest field value2", "4641", dest.getField4());
  }

  @Test
  public void testMapEmptyString_MappingLevel() throws Exception {
    Mapper mapper = getMapper(new String[] { "nullFieldMapping.xml" });
    // check that "" does not override an existing value when
    // map-empty-string="false"
    AnotherTestObject src = newInstance(AnotherTestObject.class);
    src.setField3("");
    src.setField4("");
    AnotherTestObjectPrime dest = newInstance(AnotherTestObjectPrime.class);
    dest.setTo(newInstance(TestObject.class));
    dest.setField3("555");
    dest.getTo().setOne("4641");

    // dest field should remain unchanged
    mapper.map(src, dest);
    assertEquals("invalid dest field value", "555", dest.getField3());
    assertEquals("invalid dest field value2", "4641", dest.getTo().getOne());
  }

  @Test
  public void testMapEmptyString_MappingLevel2() throws Exception {
    Mapper mapper = getMapper(new String[] { "nullFieldMapping.xml" });
    // reverse mapping
    AnotherTestObjectPrime src = newInstance(AnotherTestObjectPrime.class);
    src.setTo(newInstance(TestObject.class));
    src.setField3("");
    src.getTo().setOne("");
    AnotherTestObject dest = newInstance(AnotherTestObject.class);
    dest.setField3("555");
    dest.setField4("4641");

    // dest field should remain unchanged
    mapper.map(src, dest);
    assertEquals("invalid dest field value", "555", dest.getField3());
    assertEquals("invalid dest field value2", "4641", dest.getField4());
  }

  @Test
  public void testMapNull_ClassLevel() throws Exception {
    Mapper mapper = getMapper(new String[] { "nullFieldMapping.xml" });
    // check that null does not override an existing value when map-null="false"
    TestObject src = newInstance(TestObject.class);
    src.setOne(null);
    TestObjectPrime2 dest = newInstance(TestObjectPrime2.class);
    dest.setOne("555");

    // dest field should remain unchanged
    mapper.map(src, dest);
    assertNotNull("dest should not be null", dest.getOne());
    assertEquals("invalid dest field value", "555", dest.getOne());

    // reverse mapping
    TestObjectPrime2 src2 = newInstance(TestObjectPrime2.class);
    src2.setOne(null);
    TestObject dest2 = newInstance(TestObject.class);
    dest2.setOne("555");

    // dest field should NOT remain unchanged
    mapper.map(src2, dest2);
    assertNull("dest should be null", dest2.getOne());
  }

  @Test
  public void testMapEmptyString_ClassLevel() throws Exception {
    Mapper mapper = getMapper(new String[] { "nullFieldMapping.xml" });
    // check that "" does not override an existing value when
    // map-empty-string="false"
    TestObject src = newInstance(TestObject.class);
    src.setOne("");
    TestObjectPrime2 dest = newInstance(TestObjectPrime2.class);
    dest.setOne("555");

    // dest field should remain unchanged
    mapper.map(src, dest);
    assertNotNull("dest should not be null", dest.getOne());
    assertEquals("invalid dest field value", "555", dest.getOne());

    // reverse mapping
    TestObjectPrime2 src2 = newInstance(TestObjectPrime2.class);
    src2.setOne("");
    TestObject dest2 = newInstance(TestObject.class);
    dest2.setOne("555");

    // dest field should NOT remain unchanged
    mapper.map(src2, dest2);
    assertNotNull("dest should not be null", dest2.getOne());
    assertEquals("dest should be an empty string", "", dest2.getOne());

  }

  @Test
  public void testContextMappingWithNestedContext() throws Exception {
    Mapper mapper = getMapper(new String[] { "contextMapping.xml" });

    ContextMappingNested cmn = newInstance(ContextMappingNested.class);
    cmn.setLoanNo("loanNoNested");
    List<ContextMappingNested> list = newInstance(ArrayList.class);
    list.add(cmn);
    ContextMapping cm = newInstance(ContextMapping.class);
    cm.setLoanNo("loanNo");
    cm.setContextList(list);

    ContextMappingPrime cmpA = mapper.map(cm, ContextMappingPrime.class, "caseA");
    assertNull(cmpA.getLoanNo());
    assertNull(((ContextMappingNestedPrime) cmpA.getContextList().get(0)).getLoanNo());

    ContextMappingPrime cmpB = mapper.map(cm, ContextMappingPrime.class, "caseB");
    assertEquals("loanNo", cmpB.getLoanNo());
    assertEquals("loanNoNested", ((ContextMappingNested) cmpB.getContextList().get(0)).getLoanNo());

    ContextMappingNestedPrime cmn2 = newInstance(ContextMappingNestedPrime.class);
    cmn2.setLoanNo("loanNoNested");
    List<ContextMappingNestedPrime> list2 = newInstance(ArrayList.class);
    list2.add(cmn2);
    ContextMappingPrime prime = newInstance(ContextMappingPrime.class);
    prime.setLoanNo("loanNo");
    prime.setContextList(list2);

    ContextMapping cmDest = mapper.map(prime, ContextMapping.class, "caseA");
    assertNull(cmDest.getLoanNo());
    assertNull(((ContextMappingNested) cmDest.getContextList().get(0)).getLoanNo());

    ContextMapping cmpBDest = mapper.map(prime, ContextMapping.class, "caseB");
    assertEquals("loanNo", cmpBDest.getLoanNo());
    assertEquals("loanNoNested", ((ContextMappingNestedPrime) cmpBDest.getContextList().get(0)).getLoanNo());
  }

  @Test
  public void testArrayToSortedSet() {
    NamesArray from = newInstance(NamesArray.class);
    String[] names = { "John", "Bill", "Tony", "Fred", "Bruce" };
    from.setNames(names);
    NamesSortedSet to = mapper.map(from, NamesSortedSet.class);

    assertNotNull(to);
    assertEquals(names.length, to.getNames().size());
  }

  @Test
  public void testSortedSetToArray() {
    NamesSortedSet from = newInstance(NamesSortedSet.class);
    NamesArray to = null;
    SortedSet<String> names = newInstance(TreeSet.class);

    names.add("Jen");
    names.add("Sue");
    names.add("Sally");
    names.add("Jill");
    from.setNames(names);

    to = mapper.map(from, NamesArray.class);

    assertNotNull(to);
    assertEquals(names.size(), to.getNames().length);
  }

  @Test
  public void testSetToSortedSet() {
    NamesSet from = newInstance(NamesSet.class);
    NamesSortedSet to = null;
    Set<String> names = newInstance(HashSet.class);

    names.add("Red");
    names.add("Blue");
    names.add("Green");
    from.setNames(names);

    to = mapper.map(from, NamesSortedSet.class);

    assertNotNull(to);
    assertEquals(names.size(), to.getNames().size());
  }

  @Test
  public void testSortedSetToSet() {
    NamesSortedSet from = newInstance(NamesSortedSet.class);
    NamesSet to = null;
    SortedSet<String> names = newInstance(TreeSet.class);

    names.add("Bone");
    names.add("White");
    names.add("Beige");
    names.add("Ivory");
    names.add("Cream");
    names.add("Off white");
    from.setNames(names);

    to = mapper.map(from, NamesSet.class);

    assertNotNull(to);
    assertEquals(names.size(), to.getNames().size());
  }

  @Test
  public void testSetPrivateField() {
    mapper = super.getMapper(new String[] { "isaccessiblemapping.xml" });
    Foo src = newInstance(Foo.class);
    List<String> list = (ArrayList<String>) newInstance(ArrayList.class);
    list.add("test1");
    list.add("test2");
    src.setCategories(list);

    FooPrime dest = mapper.map(src, FooPrime.class);
    assertNotNull(dest);
  }

  @Test
  public void testStringToIndexedSet_UsingHint() {
    mapper = getMapper(new String[] { "indexMapping.xml" });
    Mccoy src = newInstance(Mccoy.class);
    src.setStringProperty(String.valueOf(System.currentTimeMillis()));
    src.setField2("someValue");

    MccoyPrime dest = mapper.map(src, MccoyPrime.class, "usingDestHint");
    Set<?> destSet = dest.getFieldValueObjects();
    assertNotNull("dest set should not be null", destSet);
    assertEquals("dest set should contain 1 entry", 1, destSet.size());
    Object entry = destSet.iterator().next();
    assertTrue("dest set entry should be instance of FieldValue", entry instanceof FieldValue);
    assertEquals("invalid value for dest object", src.getStringProperty(), ((FieldValue) entry).getValue("theKey"));
  }

  @Test
  public void testAllowedExceptions() throws Exception {
    Mapper mapper = getMapper(new String[] { "allowedExceptionsMapping.xml" });
    TestObject to = newInstance(TestObject.class);
    to.setThrowAllowedExceptionOnMap("throw me");
    try {
      mapper.map(to, TestObjectPrime.class);
      fail("We should have thrown TestException");
    } catch (RuntimeException e) {
      if (e instanceof TestException) {
        assertTrue(true);
      } else {
        fail("This should be an instance of TestException");
      }
    }
    TestObject to2 = newInstance(TestObject.class);
    to2.setThrowNonAllowedExceptionOnMap("do not throw me");
    try {
      mapper.map(to2, TestObjectPrime.class);
    } catch (RuntimeException e) {
      fail("This should not have been thrown");
    }
  }

  @Test
  public void testAllowedExceptions_Implicit() throws Exception {
    Mapper mapper = getMapper(new String[] { "implicitAllowedExceptionsMapping.xml" });
    ThrowException to = newInstance(ThrowException.class);
    to.setThrowAllowedException("throw me");
    try {
      mapper.map(to, ThrowExceptionPrime.class);
      fail("We should have thrown TestException");
    } catch (RuntimeException e) {
      if (e instanceof TestException) {
        assertTrue(true);
      } else {
        fail("This should be an instance of TestException");
      }
    }
    ThrowException to2 = newInstance(ThrowException.class);
    to2.setThrowNotAllowedException("do not throw me");
    try {
      mapper.map(to2, ThrowExceptionPrime.class);
    } catch (RuntimeException e) {
      fail("This should not have been thrown");
    }
  }

  @Test
  public void testPrimitiveArrayToList() throws Exception {
    mapper = getMapper(new String[] { "primitiveArrayToListMapping.xml" });

    int[] i = new int[] { 1, 2, 3 };
    PrimitiveArrayObj src = newInstance(PrimitiveArrayObj.class);
    src.setField1(i);

    PrimitiveArrayObjPrime dest = mapper.map(src, PrimitiveArrayObjPrime.class);

    assertNotNull("dest list field should not be null", dest.getField1());
    assertEquals("invalid dest field size", i.length, dest.getField1().size());

    List<?> srcObjectList = CollectionUtils.convertPrimitiveArrayToList(i);
    assertEquals("invalid dest field value", srcObjectList, dest.getField1());
  }

  @Test
  public void testPrimitiveArrayToList_UsingHint() throws Exception {
    mapper = getMapper(new String[] { "primitiveArrayToListMapping.xml" });

    int[] srcArray = new int[] { 1, 2, 3 };
    PrimitiveArrayObj src = newInstance(PrimitiveArrayObj.class);
    src.setField1(srcArray);

    PrimitiveArrayObjPrime dest = mapper.map(src, PrimitiveArrayObjPrime.class, "primitiveToArrayUsingHint");

    assertNotNull("dest list field should not be null", dest.getField1());
    assertEquals("invalid dest field size", srcArray.length, dest.getField1().size());

    for (int i = 0; i < srcArray.length; i++) {
      String srcValue = String.valueOf(srcArray[i]);
      String resultValue = (String) dest.getField1().get(i);
      assertEquals("invalid result entry value", srcValue, resultValue);
    }
  }

  @Test
  public void testInterface() throws Exception {
    mapper = getMapper(new String[] { "interfaceMapping.xml" });
    ApplicationUser user = newInstance(ApplicationUser.class);
    user.setSubscriberNumber("123");

    // Mapping works
    UpdateMember destObject = mapper.map(user, UpdateMember.class);

    assertEquals("invalid value for subsriber #", user.getSubscriberNumber(), destObject.getSubscriberKey().getSubscriberNumber());

    // Clear value
    destObject = new UpdateMember();

    // Mapping doesn't work
    mapper.map(user, destObject);

    assertNotNull("dest field should not be null", destObject.getSubscriberKey());
    assertEquals("invalid value for subsriber #", user.getSubscriberNumber(), destObject.getSubscriberKey().getSubscriberNumber());
  }

  @Test
  public void testCustomFieldMapper() throws Exception {
    CustomFieldMapper customFieldMapper = new TestCustomFieldMapper();
    ((DozerBeanMapper) mapper).setCustomFieldMapper(customFieldMapper);

    String currentTime = String.valueOf(System.currentTimeMillis());
    SimpleObj src = newInstance(SimpleObj.class);
    src.setField1(currentTime);
    src.setField6("field6Value" + currentTime);

    SimpleObjPrime dest = mapper.map(src, SimpleObjPrime.class);

    assertNotNull("dest field1 should not be null", dest.getField1());
    assertNotNull("dest field6 should not be null", dest.getField6());
    assertEquals("dest field1 should have been set by custom field mapper", TestCustomFieldMapper.FIELD_VALUE, dest.getField1());
    assertEquals("dest field6 should NOT have been set by custom field mapper", src.getField6(), dest.getField6());
  }

  @Test
  public void testPrivateConstructor() throws Exception {
    PrivateConstructorBean src = PrivateConstructorBean.newInstance();
    src.setField1("someValue");

    PrivateConstructorBeanPrime dest = mapper.map(src, PrivateConstructorBeanPrime.class);

    assertNotNull("dest bean should not be null", dest);
    assertEquals("field1 not mapped correctly", src.getField1(), dest.getField1());
  }

  /*
   * Bug #1549738
   */
  @Test
  public void testSetMapping_UppercaseFieldNameInXML() throws Exception {
    // For some reason the resulting SomeVO contains a Set with 4 objects. 2 SomeOtherDTO's and 2 SomeOtherVO's. I
    // believe it
    // should only contain 2 SomeOtherVO's. It has something to do with specifying the field name starting with cap in
    // the mapping file. If
    // you change the field mapping to start with lower case it seems to map correctly.
    Mapper mapper = getMapper(new String[] { "setMappingWithUpperCaseFieldName.xml" });

    SomeDTO someDto = newInstance(SomeDTO.class);
    someDto.setField1(new Integer("1"));

    SomeOtherDTO someOtherDto = newInstance(SomeOtherDTO.class);
    someOtherDto.setOtherField2(someDto);
    someOtherDto.setOtherField3("value1");

    SomeDTO someDto2 = newInstance(SomeDTO.class);
    someDto2.setField1(new Integer("2"));

    SomeOtherDTO someOtherDto2 = newInstance(SomeOtherDTO.class);
    someOtherDto2.setOtherField2(someDto2);
    someOtherDto2.setOtherField3("value2");

    SomeDTO src = newInstance(SomeDTO.class);
    src.setField2(new SomeOtherDTO[] { someOtherDto2, someOtherDto });

    SomeVO dest = mapper.map(src, SomeVO.class);

    assertEquals("incorrect resulting set size", src.getField2().length, dest.getField2().size());
    // TODO: add more asserts
  }

  @Test
  public void testGlobalBeanFactoryAppliedToDefaultMappings() throws Exception {
    mapper = getMapper(new String[] { "global-configuration.xml" });
    TestObjectPrime dest = mapper.map(newInstance(TestObject.class), TestObjectPrime.class);

    assertNotNull("created by factory name should not be null", dest.getCreatedByFactoryName());
    assertEquals(SampleDefaultBeanFactory.class.getName(), dest.getCreatedByFactoryName());
  }

  @Test
  public void testStringToDateMapping() throws Exception {
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SS");
    String dateStr = "01/29/1975 10:45:13:25";
    TestObject sourceObj = newInstance(TestObject.class);
    sourceObj.setDateStr(dateStr);
    TestObjectPrime result = mapper.map(sourceObj, TestObjectPrime.class);
    assertEquals(df.parse(dateStr), result.getDateFromStr());
    assertEquals(dateStr, df.format(result.getDateFromStr()));

    TestObject result2 = mapper.map(result, TestObject.class);
    assertEquals(df.format(result.getDateFromStr()), result2.getDateStr());
    assertEquals(result.getDateFromStr(), df.parse(result2.getDateStr()));
  }

  @Test
  public void testMethodMapping() throws Exception {
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    MethodFieldTestObject sourceObj = newInstance(MethodFieldTestObject.class);
    sourceObj.setIntegerStr("1500");
    sourceObj.setPriceItem("3500");
    sourceObj.setFieldOne("fieldOne");
    MethodFieldTestObject2 result = mapper.map(sourceObj, MethodFieldTestObject2.class);
    assertEquals("invalid result object size", 1, result.getIntegerList().size());
    assertEquals("invalid result object value", 3500, result.getTotalPrice());
    assertEquals("invalid result object value", "fieldOne", result.getFieldOne());
    // map back
    MethodFieldTestObject result2 = mapper.map(result, MethodFieldTestObject.class);
    // if no exceptions we thrown we are good. stopOnErrors = true. both values will be null
    // since this is a one-way mapping we shouldn't have a value
    assertNull(result2.getFieldOne());
  }

  @Test
  public void testNoReadMethod() throws Exception {
    // If the field doesnt have a getter/setter, dont add it is a default field to be mapped.
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    NoReadMethod src = newInstance(NoReadMethod.class);
    src.setNoReadMethod("somevalue");

    NoReadMethodPrime dest = mapper.map(src, NoReadMethodPrime.class);
    assertNull("field should be null because no read method exists for field", dest.getXXXXX());
  }

  @Test
  public void testNoReadMethodSameClassTypes() throws Exception {
    // If the field doesnt have a getter/setter, dont add it is a default field to be mapped.
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    NoReadMethod src = newInstance(NoReadMethod.class);
    src.setNoReadMethod("somevalue");

    NoReadMethod dest = mapper.map(src, NoReadMethod.class);
    assertNull("field should be null because no read method exists for field", dest.getXXXXX());
  }

  @Test
  public void testNoReadMethod_GetterOnlyWithParams() throws Exception {
    // Dont use getter methods that have a param when discovering default fields to be mapped.
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    NoReadMethod src = newInstance(NoReadMethod.class);
    src.setOtherNoReadMethod("someValue");

    NoReadMethod dest = mapper.map(src, NoReadMethod.class);
    assertNull("field should be null because no read method exists for field", dest.getOtherNoReadMethod(-1));
  }

  @Test
  public void testNoWriteMethod() throws Exception {
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    NoWriteMethod src = newInstance(NoWriteMethod.class);
    src.setXXXXXX("someValue");

    NoWriteMethodPrime dest = mapper.map(src, NoWriteMethodPrime.class);
    assertNull("field should be null because no write method exists for field", dest.getNoWriteMethod());

  }

  @Test
  public void testNoWriteMethodSameClassTypes() throws Exception {
    // When mapping between identical types, if the field doesnt have a getter/setter, dont
    // add it is a default field to be mapped.
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    NoWriteMethod src = newInstance(NoWriteMethod.class);
    src.setXXXXXX("someValue");

    mapper.map(newInstance(NoReadMethod.class), NoReadMethod.class);

    NoWriteMethod dest = mapper.map(src, NoWriteMethod.class);
    assertNull("field should be null because no write method exists for field", dest.getNoWriteMethod());
  }

  @Test
  public void testNullField() throws Exception {
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    AnotherTestObject src = newInstance(AnotherTestObject.class);
    src.setField2(null);
    AnotherTestObjectPrime dest = newInstance(AnotherTestObjectPrime.class);
    dest.setField2(Integer.valueOf("555"));
    // check that null overrides an existing value
    mapper.map(src, dest);
    assertNull("dest field should be null", dest.getField2());
  }

  @Test
  public void testNullField2() throws Exception {
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    // Test that String --> String with an empty String input value results
    // in the destination field being an empty String and not null.
    String input = "";
    TestObject src = newInstance(TestObject.class);
    src.setOne(input);

    TestObjectPrime dest = mapper.map(src, TestObjectPrime.class);
    assertNotNull("dest field should not be null", dest.getOnePrime());
    assertEquals("invalid dest field value", input, dest.getOnePrime());
  }

  @Test
  public void testNullToPrimitive() throws Exception {
    mapper = getMapper(new String[] { "dozerBeanMapping.xml" });
    AnotherTestObject src = newInstance(AnotherTestObject.class);
    AnotherTestObjectPrime prime = newInstance(AnotherTestObjectPrime.class);
    TestObject to = newInstance(TestObject.class);
    to.setThePrimitive(AnotherTestObjectPrime.DEFAULT_FIELD1);
    prime.setTo(to);
    mapper.map(src, prime);
    // check primitive on deep field
    // primitive should still be default
    assertEquals("invalid field value", AnotherTestObjectPrime.DEFAULT_FIELD1, prime.getField1());
    assertEquals("invalid field value", AnotherTestObjectPrime.DEFAULT_FIELD1, prime.getTo().getThePrimitive());
  }

  @Test
  public void testGlobalRelationshipType() throws Exception {
    mapper = getMapper(new String[] { "relationship-type-global-configuration.xml" });
    TestObject src = new TestObject();
    src.setHintList(new ArrayList<String>(Arrays.asList(new String[] { "a" })));

    TestObjectPrime dest = new TestObjectPrime();
    dest.setHintList(new ArrayList<String>(Arrays.asList(new String[] { "a", "b" })));

    mapper.map(src, dest);

    assertEquals("wrong # of elements in dest list for non-cumulative mapping", 2, dest.getHintList().size());
  }

  @Test
  public void testClassMapRelationshipType() throws Exception {
    mapper = getMapper(new String[] { "relationshipTypeMapping.xml" });
    TestObject src = new TestObject();
    src.setHintList(new ArrayList<String>(Arrays.asList(new String[] { "a" })));

    TestObjectPrime dest = new TestObjectPrime();
    dest.setHintList(new ArrayList<String>(Arrays.asList(new String[] { "a", "b" })));

    mapper.map(src, dest);

    assertEquals("wrong # of elements in dest list for non-cumulative mapping", 2, dest.getHintList().size());
  }

  @Test
  public void testRemoveOrphans() {
    mapper = getMapper(new String[] { "removeOrphansMapping.xml" });

    MyClassA myClassA = new MyClassA();
    MyClassB myClassB = new MyClassB();

    Fruit apple = new Fruit();
    apple.setName("Apple");
    Fruit banana = new Fruit();
    banana.setName("Banana");
    Fruit grape = new Fruit();
    grape.setName("Grape");
    Fruit orange = new Fruit();
    orange.setName("Orange");
    Fruit kiwiFruit = new Fruit();
    kiwiFruit.setName("Kiwi Fruit");

    List<Fruit> srcFruits = new ArrayList<Fruit>();
    srcFruits.add(apple);
    srcFruits.add(banana);
    srcFruits.add(kiwiFruit);

    List<Fruit> destFruits = new ArrayList<Fruit>();
    destFruits.add(grape); // not in src
    destFruits.add(banana); // shared with src fruits
    destFruits.add(orange); // not in src

    myClassA.setAStringList(srcFruits);
    myClassB.setAStringList(destFruits);

    mapper.map(myClassA, myClassB, "testRemoveOrphansOnList");

    assertEquals(3, myClassB.getAStringList().size());
    assertTrue(myClassB.getAStringList().contains(apple));
    assertTrue(myClassB.getAStringList().contains(banana));
    assertTrue(myClassB.getAStringList().contains(kiwiFruit));
    assertFalse(myClassB.getAStringList().contains(grape));
    assertFalse(myClassB.getAStringList().contains(orange));
  }

  @Test
  public void testOrphanRemovalSet() {
    mapper = getMapper(new String[] { "removeOrphansMapping.xml" });
    Parent parent = new Parent(new Long(1), "parent");
    Child child1 = new Child(new Long(1), "child1");
    Set<Child> childrenSet = new HashSet<Child>();
    childrenSet.add(child1);
    parent.setChildrenSet(childrenSet);

    ParentPrime parentPrime = mapper.map(parent, ParentPrime.class);
    // Make sure the first one was mapped ok.
    assertEquals(parent.getChildrenSet().size(), parentPrime.getChildrenSet().size());

    ChildPrime child2 = new ChildPrime(new Long(2L), "child2");
    parentPrime.getChildrenSet().add(child2);
    mapper.map(parentPrime, parent);
    // Make sure adding one works ok.
    assertEquals(parentPrime.getChildrenSet().size(), parent.getChildrenSet().size());

    parentPrime.getChildrenSet().clear();
    mapper.map(parentPrime, parent);
    // Make sure REMOVING them (the orphan children) works ok.
    assertEquals(parentPrime.getChildrenSet().size(), parent.getChildrenSet().size());
  }

  @Test
  public void testOrphanRemovalList() {
    mapper = getMapper(new String[] { "removeOrphansMapping.xml" });
    Parent parent = new Parent(new Long(1), "parent");
    Child child1 = new Child(new Long(1), "child1");
    List<Child> childrenList = new ArrayList<Child>();
    childrenList.add(child1);
    parent.setChildrenList(childrenList);

    ParentPrime parentPrime = mapper.map(parent, ParentPrime.class);
    // Make sure the first one was mapped ok.
    assertEquals(parent.getChildrenList().size(), parentPrime.getChildrenList().size());

    ChildPrime child2 = new ChildPrime(new Long(2L), "child2");
    parentPrime.getChildrenList().add(child2);
    mapper.map(parentPrime, parent);
    // Make sure adding one works ok.
    assertEquals(parentPrime.getChildrenList().size(), parent.getChildrenList().size());

    parentPrime.getChildrenList().clear();
    mapper.map(parentPrime, parent);
    // Make sure REMOVING them (the orphan children) works ok.
    assertEquals(parentPrime.getChildrenList().size(), parent.getChildrenList().size());
  }

  protected DataObjectInstantiator getDataObjectInstantiator() {
    return NoProxyDataObjectInstantiator.INSTANCE;
  }

}