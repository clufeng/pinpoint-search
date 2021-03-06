/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.p.s.pinpoint.thrift.dto.command;

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
public class TCommandThreadDump implements org.apache.thrift.TBase<TCommandThreadDump, TCommandThreadDump._Fields>, java.io.Serializable, Cloneable, Comparable<TCommandThreadDump> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCommandThreadDump");

  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PENDING_TIME_MILLIS_FIELD_DESC = new org.apache.thrift.protocol.TField("pendingTimeMillis", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TCommandThreadDumpStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TCommandThreadDumpTupleSchemeFactory());
  }

  private TThreadDumpType type; // required
  private String name; // optional
  private long pendingTimeMillis; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see TThreadDumpType
     */
    TYPE((short)1, "type"),
    NAME((short)2, "name"),
    PENDING_TIME_MILLIS((short)3, "pendingTimeMillis");

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
        case 1: // TYPE
          return TYPE;
        case 2: // NAME
          return NAME;
        case 3: // PENDING_TIME_MILLIS
          return PENDING_TIME_MILLIS;
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
  private static final int __PENDINGTIMEMILLIS_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.NAME, _Fields.PENDING_TIME_MILLIS};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TThreadDumpType.class)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PENDING_TIME_MILLIS, new org.apache.thrift.meta_data.FieldMetaData("pendingTimeMillis", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCommandThreadDump.class, metaDataMap);
  }

  public TCommandThreadDump() {
    this.type = TThreadDumpType.TARGET;

  }

  public TCommandThreadDump(
    TThreadDumpType type)
  {
    this();
    this.type = type;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCommandThreadDump(TCommandThreadDump other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.pendingTimeMillis = other.pendingTimeMillis;
  }

  public TCommandThreadDump deepCopy() {
    return new TCommandThreadDump(this);
  }

  @Override
  public void clear() {
    this.type = TThreadDumpType.TARGET;

    this.name = null;
    setPendingTimeMillisIsSet(false);
    this.pendingTimeMillis = 0;
  }

  /**
   * 
   * @see TThreadDumpType
   */
  public TThreadDumpType getType() {
    return this.type;
  }

  /**
   * 
   * @see TThreadDumpType
   */
  public void setType(TThreadDumpType type) {
    this.type = type;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public long getPendingTimeMillis() {
    return this.pendingTimeMillis;
  }

  public void setPendingTimeMillis(long pendingTimeMillis) {
    this.pendingTimeMillis = pendingTimeMillis;
    setPendingTimeMillisIsSet(true);
  }

  public void unsetPendingTimeMillis() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PENDINGTIMEMILLIS_ISSET_ID);
  }

  /** Returns true if field pendingTimeMillis is set (has been assigned a value) and false otherwise */
  public boolean isSetPendingTimeMillis() {
    return EncodingUtils.testBit(__isset_bitfield, __PENDINGTIMEMILLIS_ISSET_ID);
  }

  public void setPendingTimeMillisIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PENDINGTIMEMILLIS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((TThreadDumpType)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case PENDING_TIME_MILLIS:
      if (value == null) {
        unsetPendingTimeMillis();
      } else {
        setPendingTimeMillis((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case TYPE:
      return getType();

    case NAME:
      return getName();

    case PENDING_TIME_MILLIS:
      return Long.valueOf(getPendingTimeMillis());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case TYPE:
      return isSetType();
    case NAME:
      return isSetName();
    case PENDING_TIME_MILLIS:
      return isSetPendingTimeMillis();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TCommandThreadDump)
      return this.equals((TCommandThreadDump)that);
    return false;
  }

  public boolean equals(TCommandThreadDump that) {
    if (that == null)
      return false;

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_pendingTimeMillis = true && this.isSetPendingTimeMillis();
    boolean that_present_pendingTimeMillis = true && that.isSetPendingTimeMillis();
    if (this_present_pendingTimeMillis || that_present_pendingTimeMillis) {
      if (!(this_present_pendingTimeMillis && that_present_pendingTimeMillis))
        return false;
      if (this.pendingTimeMillis != that.pendingTimeMillis)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_type = true && (isSetType());
    list.add(present_type);
    if (present_type)
      list.add(type.getValue());

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_pendingTimeMillis = true && (isSetPendingTimeMillis());
    list.add(present_pendingTimeMillis);
    if (present_pendingTimeMillis)
      list.add(pendingTimeMillis);

    return list.hashCode();
  }

  @Override
  public int compareTo(TCommandThreadDump other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPendingTimeMillis()).compareTo(other.isSetPendingTimeMillis());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPendingTimeMillis()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pendingTimeMillis, other.pendingTimeMillis);
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
    StringBuilder sb = new StringBuilder("TCommandThreadDump(");
    boolean first = true;

    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (isSetName()) {
      if (!first) sb.append(", ");
      sb.append("name:");
      if (this.name == null) {
        sb.append("null");
      } else {
        sb.append(this.name);
      }
      first = false;
    }
    if (isSetPendingTimeMillis()) {
      if (!first) sb.append(", ");
      sb.append("pendingTimeMillis:");
      sb.append(this.pendingTimeMillis);
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

  private static class TCommandThreadDumpStandardSchemeFactory implements SchemeFactory {
    public TCommandThreadDumpStandardScheme getScheme() {
      return new TCommandThreadDumpStandardScheme();
    }
  }

  private static class TCommandThreadDumpStandardScheme extends StandardScheme<TCommandThreadDump> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCommandThreadDump struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.type = TThreadDumpType.findByValue(iprot.readI32());
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PENDING_TIME_MILLIS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.pendingTimeMillis = iprot.readI64();
              struct.setPendingTimeMillisIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCommandThreadDump struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeI32(struct.type.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.name != null) {
        if (struct.isSetName()) {
          oprot.writeFieldBegin(NAME_FIELD_DESC);
          oprot.writeString(struct.name);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetPendingTimeMillis()) {
        oprot.writeFieldBegin(PENDING_TIME_MILLIS_FIELD_DESC);
        oprot.writeI64(struct.pendingTimeMillis);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCommandThreadDumpTupleSchemeFactory implements SchemeFactory {
    public TCommandThreadDumpTupleScheme getScheme() {
      return new TCommandThreadDumpTupleScheme();
    }
  }

  private static class TCommandThreadDumpTupleScheme extends TupleScheme<TCommandThreadDump> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCommandThreadDump struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetType()) {
        optionals.set(0);
      }
      if (struct.isSetName()) {
        optionals.set(1);
      }
      if (struct.isSetPendingTimeMillis()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetType()) {
        oprot.writeI32(struct.type.getValue());
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetPendingTimeMillis()) {
        oprot.writeI64(struct.pendingTimeMillis);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCommandThreadDump struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.type = TThreadDumpType.findByValue(iprot.readI32());
        struct.setTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.pendingTimeMillis = iprot.readI64();
        struct.setPendingTimeMillisIsSet(true);
      }
    }
  }

}

