/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.p.s.c.thrift.dto;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import javax.annotation.Generated;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-6-19")
public class TIntStringStringValue implements org.apache.thrift.TBase<TIntStringStringValue, TIntStringStringValue._Fields>, java.io.Serializable, Cloneable, Comparable<TIntStringStringValue> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TIntStringStringValue");

  private static final org.apache.thrift.protocol.TField INT_VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("intValue", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField STRING_VALUE1_FIELD_DESC = new org.apache.thrift.protocol.TField("stringValue1", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField STRING_VALUE2_FIELD_DESC = new org.apache.thrift.protocol.TField("stringValue2", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TIntStringStringValueStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TIntStringStringValueTupleSchemeFactory());
  }

  private int intValue; // required
  private String stringValue1; // optional
  private String stringValue2; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INT_VALUE((short)1, "intValue"),
    STRING_VALUE1((short)2, "stringValue1"),
    STRING_VALUE2((short)3, "stringValue2");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // INT_VALUE
          return INT_VALUE;
        case 2: // STRING_VALUE1
          return STRING_VALUE1;
        case 3: // STRING_VALUE2
          return STRING_VALUE2;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __INTVALUE_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.STRING_VALUE1, _Fields.STRING_VALUE2};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.INT_VALUE, new org.apache.thrift.meta_data.FieldMetaData("intValue", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.STRING_VALUE1, new org.apache.thrift.meta_data.FieldMetaData("stringValue1", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STRING_VALUE2, new org.apache.thrift.meta_data.FieldMetaData("stringValue2", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TIntStringStringValue.class, metaDataMap);
  }

  public TIntStringStringValue() {
  }

  public TIntStringStringValue(
    int intValue)
  {
    this();
    this.intValue = intValue;
    setIntValueIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TIntStringStringValue(TIntStringStringValue other) {
    __isset_bitfield = other.__isset_bitfield;
    this.intValue = other.intValue;
    if (other.isSetStringValue1()) {
      this.stringValue1 = other.stringValue1;
    }
    if (other.isSetStringValue2()) {
      this.stringValue2 = other.stringValue2;
    }
  }

  public TIntStringStringValue deepCopy() {
    return new TIntStringStringValue(this);
  }

  @Override
  public void clear() {
    setIntValueIsSet(false);
    this.intValue = 0;
    this.stringValue1 = null;
    this.stringValue2 = null;
  }

  public int getIntValue() {
    return this.intValue;
  }

  public void setIntValue(int intValue) {
    this.intValue = intValue;
    setIntValueIsSet(true);
  }

  public void unsetIntValue() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __INTVALUE_ISSET_ID);
  }

  /** Returns true if field intValue is set (has been assigned a value) and false otherwise */
  public boolean isSetIntValue() {
    return EncodingUtils.testBit(__isset_bitfield, __INTVALUE_ISSET_ID);
  }

  public void setIntValueIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __INTVALUE_ISSET_ID, value);
  }

  public String getStringValue1() {
    return this.stringValue1;
  }

  public void setStringValue1(String stringValue1) {
    this.stringValue1 = stringValue1;
  }

  public void unsetStringValue1() {
    this.stringValue1 = null;
  }

  /** Returns true if field stringValue1 is set (has been assigned a value) and false otherwise */
  public boolean isSetStringValue1() {
    return this.stringValue1 != null;
  }

  public void setStringValue1IsSet(boolean value) {
    if (!value) {
      this.stringValue1 = null;
    }
  }

  public String getStringValue2() {
    return this.stringValue2;
  }

  public void setStringValue2(String stringValue2) {
    this.stringValue2 = stringValue2;
  }

  public void unsetStringValue2() {
    this.stringValue2 = null;
  }

  /** Returns true if field stringValue2 is set (has been assigned a value) and false otherwise */
  public boolean isSetStringValue2() {
    return this.stringValue2 != null;
  }

  public void setStringValue2IsSet(boolean value) {
    if (!value) {
      this.stringValue2 = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case INT_VALUE:
      if (value == null) {
        unsetIntValue();
      } else {
        setIntValue((Integer)value);
      }
      break;

    case STRING_VALUE1:
      if (value == null) {
        unsetStringValue1();
      } else {
        setStringValue1((String)value);
      }
      break;

    case STRING_VALUE2:
      if (value == null) {
        unsetStringValue2();
      } else {
        setStringValue2((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case INT_VALUE:
      return Integer.valueOf(getIntValue());

    case STRING_VALUE1:
      return getStringValue1();

    case STRING_VALUE2:
      return getStringValue2();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case INT_VALUE:
      return isSetIntValue();
    case STRING_VALUE1:
      return isSetStringValue1();
    case STRING_VALUE2:
      return isSetStringValue2();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TIntStringStringValue)
      return this.equals((TIntStringStringValue)that);
    return false;
  }

  public boolean equals(TIntStringStringValue that) {
    if (that == null)
      return false;

    boolean this_present_intValue = true;
    boolean that_present_intValue = true;
    if (this_present_intValue || that_present_intValue) {
      if (!(this_present_intValue && that_present_intValue))
        return false;
      if (this.intValue != that.intValue)
        return false;
    }

    boolean this_present_stringValue1 = true && this.isSetStringValue1();
    boolean that_present_stringValue1 = true && that.isSetStringValue1();
    if (this_present_stringValue1 || that_present_stringValue1) {
      if (!(this_present_stringValue1 && that_present_stringValue1))
        return false;
      if (!this.stringValue1.equals(that.stringValue1))
        return false;
    }

    boolean this_present_stringValue2 = true && this.isSetStringValue2();
    boolean that_present_stringValue2 = true && that.isSetStringValue2();
    if (this_present_stringValue2 || that_present_stringValue2) {
      if (!(this_present_stringValue2 && that_present_stringValue2))
        return false;
      if (!this.stringValue2.equals(that.stringValue2))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_intValue = true;
    list.add(present_intValue);
    if (present_intValue)
      list.add(intValue);

    boolean present_stringValue1 = true && (isSetStringValue1());
    list.add(present_stringValue1);
    if (present_stringValue1)
      list.add(stringValue1);

    boolean present_stringValue2 = true && (isSetStringValue2());
    list.add(present_stringValue2);
    if (present_stringValue2)
      list.add(stringValue2);

    return list.hashCode();
  }

  @Override
  public int compareTo(TIntStringStringValue other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetIntValue()).compareTo(other.isSetIntValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIntValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.intValue, other.intValue);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStringValue1()).compareTo(other.isSetStringValue1());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStringValue1()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stringValue1, other.stringValue1);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStringValue2()).compareTo(other.isSetStringValue2());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStringValue2()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stringValue2, other.stringValue2);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TIntStringStringValue(");
    boolean first = true;

    sb.append("intValue:");
    sb.append(this.intValue);
    first = false;
    if (isSetStringValue1()) {
      if (!first) sb.append(", ");
      sb.append("stringValue1:");
      if (this.stringValue1 == null) {
        sb.append("null");
      } else {
        sb.append(this.stringValue1);
      }
      first = false;
    }
    if (isSetStringValue2()) {
      if (!first) sb.append(", ");
      sb.append("stringValue2:");
      if (this.stringValue2 == null) {
        sb.append("null");
      } else {
        sb.append(this.stringValue2);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TIntStringStringValueStandardSchemeFactory implements SchemeFactory {
    public TIntStringStringValueStandardScheme getScheme() {
      return new TIntStringStringValueStandardScheme();
    }
  }

  private static class TIntStringStringValueStandardScheme extends StandardScheme<TIntStringStringValue> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TIntStringStringValue struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INT_VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.intValue = iprot.readI32();
              struct.setIntValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // STRING_VALUE1
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stringValue1 = iprot.readString();
              struct.setStringValue1IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STRING_VALUE2
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stringValue2 = iprot.readString();
              struct.setStringValue2IsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TIntStringStringValue struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(INT_VALUE_FIELD_DESC);
      oprot.writeI32(struct.intValue);
      oprot.writeFieldEnd();
      if (struct.stringValue1 != null) {
        if (struct.isSetStringValue1()) {
          oprot.writeFieldBegin(STRING_VALUE1_FIELD_DESC);
          oprot.writeString(struct.stringValue1);
          oprot.writeFieldEnd();
        }
      }
      if (struct.stringValue2 != null) {
        if (struct.isSetStringValue2()) {
          oprot.writeFieldBegin(STRING_VALUE2_FIELD_DESC);
          oprot.writeString(struct.stringValue2);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TIntStringStringValueTupleSchemeFactory implements SchemeFactory {
    public TIntStringStringValueTupleScheme getScheme() {
      return new TIntStringStringValueTupleScheme();
    }
  }

  private static class TIntStringStringValueTupleScheme extends TupleScheme<TIntStringStringValue> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TIntStringStringValue struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetIntValue()) {
        optionals.set(0);
      }
      if (struct.isSetStringValue1()) {
        optionals.set(1);
      }
      if (struct.isSetStringValue2()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetIntValue()) {
        oprot.writeI32(struct.intValue);
      }
      if (struct.isSetStringValue1()) {
        oprot.writeString(struct.stringValue1);
      }
      if (struct.isSetStringValue2()) {
        oprot.writeString(struct.stringValue2);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TIntStringStringValue struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.intValue = iprot.readI32();
        struct.setIntValueIsSet(true);
      }
      if (incoming.get(1)) {
        struct.stringValue1 = iprot.readString();
        struct.setStringValue1IsSet(true);
      }
      if (incoming.get(2)) {
        struct.stringValue2 = iprot.readString();
        struct.setStringValue2IsSet(true);
      }
    }
  }

}

