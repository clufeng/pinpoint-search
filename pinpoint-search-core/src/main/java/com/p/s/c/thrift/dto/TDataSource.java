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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2017-2-22")
public class TDataSource implements org.apache.thrift.TBase<TDataSource, TDataSource._Fields>, java.io.Serializable, Cloneable, Comparable<TDataSource> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TDataSource");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField SERVICE_TYPE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("serviceTypeCode", org.apache.thrift.protocol.TType.I16, (short)2);
  private static final org.apache.thrift.protocol.TField DATABASE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("databaseName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField URL_FIELD_DESC = new org.apache.thrift.protocol.TField("url", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField ACTIVE_CONNECTION_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("activeConnectionSize", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField MAX_CONNECTION_SIZE_FIELD_DESC = new org.apache.thrift.protocol.TField("maxConnectionSize", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TDataSourceStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TDataSourceTupleSchemeFactory());
  }

  private int id; // required
  private short serviceTypeCode; // optional
  private String databaseName; // optional
  private String url; // optional
  private int activeConnectionSize; // optional
  private int maxConnectionSize; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    SERVICE_TYPE_CODE((short)2, "serviceTypeCode"),
    DATABASE_NAME((short)3, "databaseName"),
    URL((short)4, "url"),
    ACTIVE_CONNECTION_SIZE((short)5, "activeConnectionSize"),
    MAX_CONNECTION_SIZE((short)6, "maxConnectionSize");

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
        case 1: // ID
          return ID;
        case 2: // SERVICE_TYPE_CODE
          return SERVICE_TYPE_CODE;
        case 3: // DATABASE_NAME
          return DATABASE_NAME;
        case 4: // URL
          return URL;
        case 5: // ACTIVE_CONNECTION_SIZE
          return ACTIVE_CONNECTION_SIZE;
        case 6: // MAX_CONNECTION_SIZE
          return MAX_CONNECTION_SIZE;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __SERVICETYPECODE_ISSET_ID = 1;
  private static final int __ACTIVECONNECTIONSIZE_ISSET_ID = 2;
  private static final int __MAXCONNECTIONSIZE_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.SERVICE_TYPE_CODE, _Fields.DATABASE_NAME, _Fields.URL, _Fields.ACTIVE_CONNECTION_SIZE, _Fields.MAX_CONNECTION_SIZE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SERVICE_TYPE_CODE, new org.apache.thrift.meta_data.FieldMetaData("serviceTypeCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.DATABASE_NAME, new org.apache.thrift.meta_data.FieldMetaData("databaseName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL, new org.apache.thrift.meta_data.FieldMetaData("url", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ACTIVE_CONNECTION_SIZE, new org.apache.thrift.meta_data.FieldMetaData("activeConnectionSize", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.MAX_CONNECTION_SIZE, new org.apache.thrift.meta_data.FieldMetaData("maxConnectionSize", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TDataSource.class, metaDataMap);
  }

  public TDataSource() {
    this.activeConnectionSize = 0;

  }

  public TDataSource(
    int id)
  {
    this();
    this.id = id;
    setIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TDataSource(TDataSource other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.serviceTypeCode = other.serviceTypeCode;
    if (other.isSetDatabaseName()) {
      this.databaseName = other.databaseName;
    }
    if (other.isSetUrl()) {
      this.url = other.url;
    }
    this.activeConnectionSize = other.activeConnectionSize;
    this.maxConnectionSize = other.maxConnectionSize;
  }

  public TDataSource deepCopy() {
    return new TDataSource(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setServiceTypeCodeIsSet(false);
    this.serviceTypeCode = 0;
    this.databaseName = null;
    this.url = null;
    this.activeConnectionSize = 0;

    setMaxConnectionSizeIsSet(false);
    this.maxConnectionSize = 0;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
    setIdIsSet(true);
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public short getServiceTypeCode() {
    return this.serviceTypeCode;
  }

  public void setServiceTypeCode(short serviceTypeCode) {
    this.serviceTypeCode = serviceTypeCode;
    setServiceTypeCodeIsSet(true);
  }

  public void unsetServiceTypeCode() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __SERVICETYPECODE_ISSET_ID);
  }

  /** Returns true if field serviceTypeCode is set (has been assigned a value) and false otherwise */
  public boolean isSetServiceTypeCode() {
    return EncodingUtils.testBit(__isset_bitfield, __SERVICETYPECODE_ISSET_ID);
  }

  public void setServiceTypeCodeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __SERVICETYPECODE_ISSET_ID, value);
  }

  public String getDatabaseName() {
    return this.databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public void unsetDatabaseName() {
    this.databaseName = null;
  }

  /** Returns true if field databaseName is set (has been assigned a value) and false otherwise */
  public boolean isSetDatabaseName() {
    return this.databaseName != null;
  }

  public void setDatabaseNameIsSet(boolean value) {
    if (!value) {
      this.databaseName = null;
    }
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void unsetUrl() {
    this.url = null;
  }

  /** Returns true if field url is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl() {
    return this.url != null;
  }

  public void setUrlIsSet(boolean value) {
    if (!value) {
      this.url = null;
    }
  }

  public int getActiveConnectionSize() {
    return this.activeConnectionSize;
  }

  public void setActiveConnectionSize(int activeConnectionSize) {
    this.activeConnectionSize = activeConnectionSize;
    setActiveConnectionSizeIsSet(true);
  }

  public void unsetActiveConnectionSize() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ACTIVECONNECTIONSIZE_ISSET_ID);
  }

  /** Returns true if field activeConnectionSize is set (has been assigned a value) and false otherwise */
  public boolean isSetActiveConnectionSize() {
    return EncodingUtils.testBit(__isset_bitfield, __ACTIVECONNECTIONSIZE_ISSET_ID);
  }

  public void setActiveConnectionSizeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ACTIVECONNECTIONSIZE_ISSET_ID, value);
  }

  public int getMaxConnectionSize() {
    return this.maxConnectionSize;
  }

  public void setMaxConnectionSize(int maxConnectionSize) {
    this.maxConnectionSize = maxConnectionSize;
    setMaxConnectionSizeIsSet(true);
  }

  public void unsetMaxConnectionSize() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MAXCONNECTIONSIZE_ISSET_ID);
  }

  /** Returns true if field maxConnectionSize is set (has been assigned a value) and false otherwise */
  public boolean isSetMaxConnectionSize() {
    return EncodingUtils.testBit(__isset_bitfield, __MAXCONNECTIONSIZE_ISSET_ID);
  }

  public void setMaxConnectionSizeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MAXCONNECTIONSIZE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case SERVICE_TYPE_CODE:
      if (value == null) {
        unsetServiceTypeCode();
      } else {
        setServiceTypeCode((Short)value);
      }
      break;

    case DATABASE_NAME:
      if (value == null) {
        unsetDatabaseName();
      } else {
        setDatabaseName((String)value);
      }
      break;

    case URL:
      if (value == null) {
        unsetUrl();
      } else {
        setUrl((String)value);
      }
      break;

    case ACTIVE_CONNECTION_SIZE:
      if (value == null) {
        unsetActiveConnectionSize();
      } else {
        setActiveConnectionSize((Integer)value);
      }
      break;

    case MAX_CONNECTION_SIZE:
      if (value == null) {
        unsetMaxConnectionSize();
      } else {
        setMaxConnectionSize((Integer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Integer.valueOf(getId());

    case SERVICE_TYPE_CODE:
      return Short.valueOf(getServiceTypeCode());

    case DATABASE_NAME:
      return getDatabaseName();

    case URL:
      return getUrl();

    case ACTIVE_CONNECTION_SIZE:
      return Integer.valueOf(getActiveConnectionSize());

    case MAX_CONNECTION_SIZE:
      return Integer.valueOf(getMaxConnectionSize());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case SERVICE_TYPE_CODE:
      return isSetServiceTypeCode();
    case DATABASE_NAME:
      return isSetDatabaseName();
    case URL:
      return isSetUrl();
    case ACTIVE_CONNECTION_SIZE:
      return isSetActiveConnectionSize();
    case MAX_CONNECTION_SIZE:
      return isSetMaxConnectionSize();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TDataSource)
      return this.equals((TDataSource)that);
    return false;
  }

  public boolean equals(TDataSource that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_serviceTypeCode = true && this.isSetServiceTypeCode();
    boolean that_present_serviceTypeCode = true && that.isSetServiceTypeCode();
    if (this_present_serviceTypeCode || that_present_serviceTypeCode) {
      if (!(this_present_serviceTypeCode && that_present_serviceTypeCode))
        return false;
      if (this.serviceTypeCode != that.serviceTypeCode)
        return false;
    }

    boolean this_present_databaseName = true && this.isSetDatabaseName();
    boolean that_present_databaseName = true && that.isSetDatabaseName();
    if (this_present_databaseName || that_present_databaseName) {
      if (!(this_present_databaseName && that_present_databaseName))
        return false;
      if (!this.databaseName.equals(that.databaseName))
        return false;
    }

    boolean this_present_url = true && this.isSetUrl();
    boolean that_present_url = true && that.isSetUrl();
    if (this_present_url || that_present_url) {
      if (!(this_present_url && that_present_url))
        return false;
      if (!this.url.equals(that.url))
        return false;
    }

    boolean this_present_activeConnectionSize = true && this.isSetActiveConnectionSize();
    boolean that_present_activeConnectionSize = true && that.isSetActiveConnectionSize();
    if (this_present_activeConnectionSize || that_present_activeConnectionSize) {
      if (!(this_present_activeConnectionSize && that_present_activeConnectionSize))
        return false;
      if (this.activeConnectionSize != that.activeConnectionSize)
        return false;
    }

    boolean this_present_maxConnectionSize = true && this.isSetMaxConnectionSize();
    boolean that_present_maxConnectionSize = true && that.isSetMaxConnectionSize();
    if (this_present_maxConnectionSize || that_present_maxConnectionSize) {
      if (!(this_present_maxConnectionSize && that_present_maxConnectionSize))
        return false;
      if (this.maxConnectionSize != that.maxConnectionSize)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_serviceTypeCode = true && (isSetServiceTypeCode());
    list.add(present_serviceTypeCode);
    if (present_serviceTypeCode)
      list.add(serviceTypeCode);

    boolean present_databaseName = true && (isSetDatabaseName());
    list.add(present_databaseName);
    if (present_databaseName)
      list.add(databaseName);

    boolean present_url = true && (isSetUrl());
    list.add(present_url);
    if (present_url)
      list.add(url);

    boolean present_activeConnectionSize = true && (isSetActiveConnectionSize());
    list.add(present_activeConnectionSize);
    if (present_activeConnectionSize)
      list.add(activeConnectionSize);

    boolean present_maxConnectionSize = true && (isSetMaxConnectionSize());
    list.add(present_maxConnectionSize);
    if (present_maxConnectionSize)
      list.add(maxConnectionSize);

    return list.hashCode();
  }

  @Override
  public int compareTo(TDataSource other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetServiceTypeCode()).compareTo(other.isSetServiceTypeCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServiceTypeCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serviceTypeCode, other.serviceTypeCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetDatabaseName()).compareTo(other.isSetDatabaseName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDatabaseName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.databaseName, other.databaseName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl()).compareTo(other.isSetUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url, other.url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetActiveConnectionSize()).compareTo(other.isSetActiveConnectionSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetActiveConnectionSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.activeConnectionSize, other.activeConnectionSize);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMaxConnectionSize()).compareTo(other.isSetMaxConnectionSize());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMaxConnectionSize()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maxConnectionSize, other.maxConnectionSize);
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
    StringBuilder sb = new StringBuilder("TDataSource(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (isSetServiceTypeCode()) {
      if (!first) sb.append(", ");
      sb.append("serviceTypeCode:");
      sb.append(this.serviceTypeCode);
      first = false;
    }
    if (isSetDatabaseName()) {
      if (!first) sb.append(", ");
      sb.append("databaseName:");
      if (this.databaseName == null) {
        sb.append("null");
      } else {
        sb.append(this.databaseName);
      }
      first = false;
    }
    if (isSetUrl()) {
      if (!first) sb.append(", ");
      sb.append("url:");
      if (this.url == null) {
        sb.append("null");
      } else {
        sb.append(this.url);
      }
      first = false;
    }
    if (isSetActiveConnectionSize()) {
      if (!first) sb.append(", ");
      sb.append("activeConnectionSize:");
      sb.append(this.activeConnectionSize);
      first = false;
    }
    if (isSetMaxConnectionSize()) {
      if (!first) sb.append(", ");
      sb.append("maxConnectionSize:");
      sb.append(this.maxConnectionSize);
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

  private static class TDataSourceStandardSchemeFactory implements SchemeFactory {
    public TDataSourceStandardScheme getScheme() {
      return new TDataSourceStandardScheme();
    }
  }

  private static class TDataSourceStandardScheme extends StandardScheme<TDataSource> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TDataSource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SERVICE_TYPE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.serviceTypeCode = iprot.readI16();
              struct.setServiceTypeCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATABASE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.databaseName = iprot.readString();
              struct.setDatabaseNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url = iprot.readString();
              struct.setUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // ACTIVE_CONNECTION_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.activeConnectionSize = iprot.readI32();
              struct.setActiveConnectionSizeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // MAX_CONNECTION_SIZE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.maxConnectionSize = iprot.readI32();
              struct.setMaxConnectionSizeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TDataSource struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      if (struct.isSetServiceTypeCode()) {
        oprot.writeFieldBegin(SERVICE_TYPE_CODE_FIELD_DESC);
        oprot.writeI16(struct.serviceTypeCode);
        oprot.writeFieldEnd();
      }
      if (struct.databaseName != null) {
        if (struct.isSetDatabaseName()) {
          oprot.writeFieldBegin(DATABASE_NAME_FIELD_DESC);
          oprot.writeString(struct.databaseName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.url != null) {
        if (struct.isSetUrl()) {
          oprot.writeFieldBegin(URL_FIELD_DESC);
          oprot.writeString(struct.url);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetActiveConnectionSize()) {
        oprot.writeFieldBegin(ACTIVE_CONNECTION_SIZE_FIELD_DESC);
        oprot.writeI32(struct.activeConnectionSize);
        oprot.writeFieldEnd();
      }
      if (struct.isSetMaxConnectionSize()) {
        oprot.writeFieldBegin(MAX_CONNECTION_SIZE_FIELD_DESC);
        oprot.writeI32(struct.maxConnectionSize);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TDataSourceTupleSchemeFactory implements SchemeFactory {
    public TDataSourceTupleScheme getScheme() {
      return new TDataSourceTupleScheme();
    }
  }

  private static class TDataSourceTupleScheme extends TupleScheme<TDataSource> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TDataSource struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetServiceTypeCode()) {
        optionals.set(1);
      }
      if (struct.isSetDatabaseName()) {
        optionals.set(2);
      }
      if (struct.isSetUrl()) {
        optionals.set(3);
      }
      if (struct.isSetActiveConnectionSize()) {
        optionals.set(4);
      }
      if (struct.isSetMaxConnectionSize()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetServiceTypeCode()) {
        oprot.writeI16(struct.serviceTypeCode);
      }
      if (struct.isSetDatabaseName()) {
        oprot.writeString(struct.databaseName);
      }
      if (struct.isSetUrl()) {
        oprot.writeString(struct.url);
      }
      if (struct.isSetActiveConnectionSize()) {
        oprot.writeI32(struct.activeConnectionSize);
      }
      if (struct.isSetMaxConnectionSize()) {
        oprot.writeI32(struct.maxConnectionSize);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TDataSource struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.serviceTypeCode = iprot.readI16();
        struct.setServiceTypeCodeIsSet(true);
      }
      if (incoming.get(2)) {
        struct.databaseName = iprot.readString();
        struct.setDatabaseNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.url = iprot.readString();
        struct.setUrlIsSet(true);
      }
      if (incoming.get(4)) {
        struct.activeConnectionSize = iprot.readI32();
        struct.setActiveConnectionSizeIsSet(true);
      }
      if (incoming.get(5)) {
        struct.maxConnectionSize = iprot.readI32();
        struct.setMaxConnectionSizeIsSet(true);
      }
    }
  }

}

