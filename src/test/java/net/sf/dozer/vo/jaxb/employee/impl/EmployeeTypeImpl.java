//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.4-b18-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2006.10.05 at 10:03:40 CEST 
//

package net.sf.dozer.vo.jaxb.employee.impl;

public class EmployeeTypeImpl implements net.sf.dozer.vo.jaxb.employee.EmployeeType, com.sun.xml.bind.JAXBObject,
    net.sf.dozer.vo.jaxb.employee.impl.runtime.UnmarshallableObject, net.sf.dozer.vo.jaxb.employee.impl.runtime.XMLSerializable,
    net.sf.dozer.vo.jaxb.employee.impl.runtime.ValidatableObject {

  protected java.util.Calendar _BirthDate;
  protected java.lang.String _FirstName;
  protected java.lang.String _LastName;
  public final static java.lang.Class version = (net.sf.dozer.vo.jaxb.employee.impl.JAXBVersion.class);
  private static com.sun.msv.grammar.Grammar schemaFragment;

  private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
    return (net.sf.dozer.vo.jaxb.employee.EmployeeType.class);
  }

  public java.util.Calendar getBirthDate() {
    return _BirthDate;
  }

  public void setBirthDate(java.util.Calendar value) {
    _BirthDate = value;
  }

  public java.lang.String getFirstName() {
    return _FirstName;
  }

  public void setFirstName(java.lang.String value) {
    _FirstName = value;
  }

  public java.lang.String getLastName() {
    return _LastName;
  }

  public void setLastName(java.lang.String value) {
    _LastName = value;
  }

  public net.sf.dozer.vo.jaxb.employee.impl.runtime.UnmarshallingEventHandler createUnmarshaller(
      net.sf.dozer.vo.jaxb.employee.impl.runtime.UnmarshallingContext context) {
    return new net.sf.dozer.vo.jaxb.employee.impl.EmployeeTypeImpl.Unmarshaller(context);
  }

  public void serializeBody(net.sf.dozer.vo.jaxb.employee.impl.runtime.XMLSerializer context) throws org.xml.sax.SAXException {
    context.startElement("", "FirstName");
    context.endNamespaceDecls();
    context.endAttributes();
    try {
      context.text((_FirstName), "FirstName");
    } catch (java.lang.Exception e) {
      net.sf.dozer.vo.jaxb.employee.impl.runtime.Util.handlePrintConversionException(this, e, context);
    }
    context.endElement();
    if (_BirthDate != null) {
      context.startElement("", "BirthDate");
      context.endNamespaceDecls();
      context.endAttributes();
      try {
        context.text(javax.xml.bind.DatatypeConverter.printDate((_BirthDate)), "BirthDate");
      } catch (java.lang.Exception e) {
        net.sf.dozer.vo.jaxb.employee.impl.runtime.Util.handlePrintConversionException(this, e, context);
      }
      context.endElement();
    }
    context.startElement("", "LastName");
    context.endNamespaceDecls();
    context.endAttributes();
    try {
      context.text((_LastName), "LastName");
    } catch (java.lang.Exception e) {
      net.sf.dozer.vo.jaxb.employee.impl.runtime.Util.handlePrintConversionException(this, e, context);
    }
    context.endElement();
  }

  public void serializeAttributes(net.sf.dozer.vo.jaxb.employee.impl.runtime.XMLSerializer context) throws org.xml.sax.SAXException {
  }

  public void serializeURIs(net.sf.dozer.vo.jaxb.employee.impl.runtime.XMLSerializer context) throws org.xml.sax.SAXException {
  }

  public java.lang.Class getPrimaryInterface() {
    return (net.sf.dozer.vo.jaxb.employee.EmployeeType.class);
  }

  public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
    if (schemaFragment == null) {
      schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer
          .deserialize(("\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
              + "n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
              + "mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
              + "on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
              + "expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsr\u0000\'com.sun.msv.grammar.trex.Ele"
              + "mentPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/Na"
              + "meClass;xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aigno"
              + "reUndeclaredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000pps"
              + "r\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxn"
              + "g/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/uti"
              + "l/StringPair;xq\u0000~\u0000\u0003ppsr\u0000#com.sun.msv.datatype.xsd.StringType"
              + "\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xsd.Buil"
              + "tinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.Concret"
              + "eType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatypeImpl\u0000"
              + "\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~"
              + "\u0000\u0014L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpaceProces"
              + "sor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u00005com.su"
              + "n.msv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr"
              + "\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001"
              + "sr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
              + "\u0002\u0000\u0000xq\u0000~\u0000\u0003ppsr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tloca"
              + "lNameq\u0000~\u0000\u0014L\u0000\fnamespaceURIq\u0000~\u0000\u0014xpq\u0000~\u0000\u0018q\u0000~\u0000\u0017sr\u0000\u001dcom.sun.msv.gr"
              + "ammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.At"
              + "tributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\bxq\u0000~\u0000\u0003sr\u0000\u0011j"
              + "ava.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psq\u0000~\u0000\fppsr\u0000\"com.sun.m"
              + "sv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0011q\u0000~\u0000\u0017t\u0000\u0005QNamesr\u00005c"
              + "om.sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
              + "\u0002\u0000\u0000xq\u0000~\u0000\u001aq\u0000~\u0000\u001dsq\u0000~\u0000\u001eq\u0000~\u0000)q\u0000~\u0000\u0017sr\u0000#com.sun.msv.grammar.Simple"
              + "NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0014L\u0000\fnamespaceURIq\u0000~\u0000\u0014xr\u0000"
              + "\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://"
              + "www.w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Ex"
              + "pression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000$\u0001psq\u0000~\u0000-t\u0000\t"
              + "FirstNamet\u0000\u0000sq\u0000~\u0000 ppsq\u0000~\u0000\u0007q\u0000~\u0000%p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\fppsr\u0000!com.sun"
              + ".msv.datatype.xsd.DateType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000)com.sun.msv.datatyp"
              + "e.xsd.DateTimeBaseType\u0014W\u001a@3\u00a5\u00b4\u00e5\u0002\u0000\u0000xq\u0000~\u0000\u0011q\u0000~\u0000\u0017t\u0000\u0004dateq\u0000~\u0000+q\u0000~\u0000"
              + "\u001dsq\u0000~\u0000\u001eq\u0000~\u0000?q\u0000~\u0000\u0017sq\u0000~\u0000 ppsq\u0000~\u0000\"q\u0000~\u0000%pq\u0000~\u0000&q\u0000~\u0000/q\u0000~\u00003sq\u0000~\u0000-t\u0000"
              + "\tBirthDateq\u0000~\u00007q\u0000~\u00003sq\u0000~\u0000\u0007pp\u0000sq\u0000~\u0000\u0000ppq\u0000~\u0000\u000fsq\u0000~\u0000 ppsq\u0000~\u0000\"q\u0000~\u0000"
              + "%pq\u0000~\u0000&q\u0000~\u0000/q\u0000~\u00003sq\u0000~\u0000-t\u0000\bLastNameq\u0000~\u00007sr\u0000\"com.sun.msv.gramm"
              + "ar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/gramm"
              + "ar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.Expre"
              + "ssionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006pa"
              + "rentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\t\u0001pq\u0000~\u0000:q\u0000~\u0000"
              + "\u0005q\u0000~\u00008q\u0000~\u0000!q\u0000~\u0000Aq\u0000~\u0000Gq\u0000~\u0000\u000bq\u0000~\u0000Fq\u0000~\u0000\u0006x"));
    }
    return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
  }

  public class Unmarshaller extends net.sf.dozer.vo.jaxb.employee.impl.runtime.AbstractUnmarshallingEventHandlerImpl {

    public Unmarshaller(net.sf.dozer.vo.jaxb.employee.impl.runtime.UnmarshallingContext context) {
      super(context, "----------");
    }

    protected Unmarshaller(net.sf.dozer.vo.jaxb.employee.impl.runtime.UnmarshallingContext context, int startState) {
      this(context);
      state = startState;
    }

    public java.lang.Object owner() {
      return net.sf.dozer.vo.jaxb.employee.impl.EmployeeTypeImpl.this;
    }

    @Override
    public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname,
        org.xml.sax.Attributes __atts) throws org.xml.sax.SAXException {
      outer: while (true) {
        switch (state) {
        case 9:
          revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
          return;
        case 6:
          if (("LastName" == ___local) && ("" == ___uri)) {
            context.pushAttributes(__atts, true);
            state = 7;
            return;
          }
          break;
        case 3:
          if (("BirthDate" == ___local) && ("" == ___uri)) {
            context.pushAttributes(__atts, true);
            state = 4;
            return;
          }
          state = 6;
          continue outer;
        case 0:
          if (("FirstName" == ___local) && ("" == ___uri)) {
            context.pushAttributes(__atts, true);
            state = 1;
            return;
          }
          break;
        }
        super.enterElement(___uri, ___local, ___qname, __atts);
        break;
      }
    }

    @Override
    public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
        throws org.xml.sax.SAXException {
      outer: while (true) {
        switch (state) {
        case 9:
          revertToParentFromLeaveElement(___uri, ___local, ___qname);
          return;
        case 8:
          if (("LastName" == ___local) && ("" == ___uri)) {
            context.popAttributes();
            state = 9;
            return;
          }
          break;
        case 3:
          state = 6;
          continue outer;
        case 2:
          if (("FirstName" == ___local) && ("" == ___uri)) {
            context.popAttributes();
            state = 3;
            return;
          }
          break;
        case 5:
          if (("BirthDate" == ___local) && ("" == ___uri)) {
            context.popAttributes();
            state = 6;
            return;
          }
          break;
        }
        super.leaveElement(___uri, ___local, ___qname);
        break;
      }
    }

    @Override
    public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
        throws org.xml.sax.SAXException {
      outer: while (true) {
        switch (state) {
        case 9:
          revertToParentFromEnterAttribute(___uri, ___local, ___qname);
          return;
        case 3:
          state = 6;
          continue outer;
        }
        super.enterAttribute(___uri, ___local, ___qname);
        break;
      }
    }

    @Override
    public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
        throws org.xml.sax.SAXException {
      outer: while (true) {
        switch (state) {
        case 9:
          revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
          return;
        case 3:
          state = 6;
          continue outer;
        }
        super.leaveAttribute(___uri, ___local, ___qname);
        break;
      }
    }

    @Override
    public void handleText(final java.lang.String value) throws org.xml.sax.SAXException {
      outer: while (true) {
        try {
          switch (state) {
          case 9:
            revertToParentFromText(value);
            return;
          case 3:
            state = 6;
            continue outer;
          case 7:
            eatText1(value);
            state = 8;
            return;
          case 4:
            eatText2(value);
            state = 5;
            return;
          case 1:
            eatText3(value);
            state = 2;
            return;
          }
        } catch (java.lang.RuntimeException e) {
          handleUnexpectedTextException(value, e);
        }
        break;
      }
    }

    private void eatText1(final java.lang.String value) throws org.xml.sax.SAXException {
      try {
        _LastName = value;
      } catch (java.lang.Exception e) {
        handleParseConversionException(e);
      }
    }

    private void eatText2(final java.lang.String value) throws org.xml.sax.SAXException {
      try {
        _BirthDate = javax.xml.bind.DatatypeConverter.parseDate(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
      } catch (java.lang.Exception e) {
        handleParseConversionException(e);
      }
    }

    private void eatText3(final java.lang.String value) throws org.xml.sax.SAXException {
      try {
        _FirstName = value;
      } catch (java.lang.Exception e) {
        handleParseConversionException(e);
      }
    }

  }

}
