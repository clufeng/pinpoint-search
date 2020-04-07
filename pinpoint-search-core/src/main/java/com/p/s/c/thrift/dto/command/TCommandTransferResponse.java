/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.p.s.c.thrift.dto.command;

import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

import javax.annotation.Generated;
import java.nio.ByteBuffer;
import java.util.*;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-8-18")
public class TCommandTransferResponse implements org.apache.thrift.TBase<TCommandTransferResponse, TCommandTransferResponse._Fields>, java.io.Serializable, Cloneable, Comparable<TCommandTransferResponse> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCommandTransferResponse");

  private static final org.apache.thrift.protocol.TField ROUTE_RESULT_FIELD_DESC = new org.apache.thrift.protocol.TField("routeResult", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField PAYLOAD_FIELD_DESC = new org.apache.thrift.protocol.TField("payload", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField MESSAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("message", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TCommandTransferResponseStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TCommandTransferResponseTupleSchemeFactory());
  }

  private TRouteResult routeResult; // required
  private ByteBuffer payload; // required
  private String message; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see TRouteResult
     */
    ROUTE_RESULT((short)1, "routeResult"),
    PAYLOAD((short)2, "payload"),
    MESSAGE((short)3, "message");

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
        case 1: // ROUTE_RESULT
          return ROUTE_RESULT;
        case 2: // PAYLOAD
          return PAYLOAD;
        case 3: // MESSAGE
          return MESSAGE;
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
  private static final _Fields optionals[] = {_Fields.MESSAGE};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROUTE_RESULT, new org.apache.thrift.meta_data.FieldMetaData("routeResult", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TRouteResult.class)));
    tmpMap.put(_Fields.PAYLOAD, new org.apache.thrift.meta_data.FieldMetaData("payload", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    tmpMap.put(_Fields.MESSAGE, new org.apache.thrift.meta_data.FieldMetaData("message", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCommandTransferResponse.class, metaDataMap);
  }

  public TCommandTransferResponse() {
  }

  public TCommandTransferResponse(
    TRouteResult routeResult,
    ByteBuffer payload)
  {
    this();
    this.routeResult = routeResult;
    this.payload = org.apache.thrift.TBaseHelper.copyBinary(payload);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCommandTransferResponse(TCommandTransferResponse other) {
    if (other.isSetRouteResult()) {
      this.routeResult = other.routeResult;
    }
    if (other.isSetPayload()) {
      this.payload = org.apache.thrift.TBaseHelper.copyBinary(other.payload);
    }
    if (other.isSetMessage()) {
      this.message = other.message;
    }
  }

  public TCommandTransferResponse deepCopy() {
    return new TCommandTransferResponse(this);
  }

  @Override
  public void clear() {
    this.routeResult = null;
    this.payload = null;
    this.message = null;
  }

  /**
   * 
   * @see TRouteResult
   */
  public TRouteResult getRouteResult() {
    return this.routeResult;
  }

  /**
   * 
   * @see TRouteResult
   */
  public void setRouteResult(TRouteResult routeResult) {
    this.routeResult = routeResult;
  }

  public void unsetRouteResult() {
    this.routeResult = null;
  }

  /** Returns true if field routeResult is set (has been assigned a value) and false otherwise */
  public boolean isSetRouteResult() {
    return this.routeResult != null;
  }

  public void setRouteResultIsSet(boolean value) {
    if (!value) {
      this.routeResult = null;
    }
  }

  public byte[] getPayload() {
    setPayload(org.apache.thrift.TBaseHelper.rightSize(payload));
    return payload == null ? null : payload.array();
  }

  public ByteBuffer bufferForPayload() {
    return org.apache.thrift.TBaseHelper.copyBinary(payload);
  }

  public void setPayload(byte[] payload) {
    this.payload = payload == null ? (ByteBuffer)null : ByteBuffer.wrap(Arrays.copyOf(payload, payload.length));
  }

  public void setPayload(ByteBuffer payload) {
    this.payload = org.apache.thrift.TBaseHelper.copyBinary(payload);
  }

  public void unsetPayload() {
    this.payload = null;
  }

  /** Returns true if field payload is set (has been assigned a value) and false otherwise */
  public boolean isSetPayload() {
    return this.payload != null;
  }

  public void setPayloadIsSet(boolean value) {
    if (!value) {
      this.payload = null;
    }
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void unsetMessage() {
    this.message = null;
  }

  /** Returns true if field message is set (has been assigned a value) and false otherwise */
  public boolean isSetMessage() {
    return this.message != null;
  }

  public void setMessageIsSet(boolean value) {
    if (!value) {
      this.message = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ROUTE_RESULT:
      if (value == null) {
        unsetRouteResult();
      } else {
        setRouteResult((TRouteResult)value);
      }
      break;

    case PAYLOAD:
      if (value == null) {
        unsetPayload();
      } else {
        setPayload((ByteBuffer)value);
      }
      break;

    case MESSAGE:
      if (value == null) {
        unsetMessage();
      } else {
        setMessage((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ROUTE_RESULT:
      return getRouteResult();

    case PAYLOAD:
      return getPayload();

    case MESSAGE:
      return getMessage();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ROUTE_RESULT:
      return isSetRouteResult();
    case PAYLOAD:
      return isSetPayload();
    case MESSAGE:
      return isSetMessage();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TCommandTransferResponse)
      return this.equals((TCommandTransferResponse)that);
    return false;
  }

  public boolean equals(TCommandTransferResponse that) {
    if (that == null)
      return false;

    boolean this_present_routeResult = true && this.isSetRouteResult();
    boolean that_present_routeResult = true && that.isSetRouteResult();
    if (this_present_routeResult || that_present_routeResult) {
      if (!(this_present_routeResult && that_present_routeResult))
        return false;
      if (!this.routeResult.equals(that.routeResult))
        return false;
    }

    boolean this_present_payload = true && this.isSetPayload();
    boolean that_present_payload = true && that.isSetPayload();
    if (this_present_payload || that_present_payload) {
      if (!(this_present_payload && that_present_payload))
        return false;
      if (!this.payload.equals(that.payload))
        return false;
    }

    boolean this_present_message = true && this.isSetMessage();
    boolean that_present_message = true && that.isSetMessage();
    if (this_present_message || that_present_message) {
      if (!(this_present_message && that_present_message))
        return false;
      if (!this.message.equals(that.message))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_routeResult = true && (isSetRouteResult());
    list.add(present_routeResult);
    if (present_routeResult)
      list.add(routeResult.getValue());

    boolean present_payload = true && (isSetPayload());
    list.add(present_payload);
    if (present_payload)
      list.add(payload);

    boolean present_message = true && (isSetMessage());
    list.add(present_message);
    if (present_message)
      list.add(message);

    return list.hashCode();
  }

  @Override
  public int compareTo(TCommandTransferResponse other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRouteResult()).compareTo(other.isSetRouteResult());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRouteResult()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.routeResult, other.routeResult);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPayload()).compareTo(other.isSetPayload());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPayload()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.payload, other.payload);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMessage()).compareTo(other.isSetMessage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMessage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.message, other.message);
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
    StringBuilder sb = new StringBuilder("TCommandTransferResponse(");
    boolean first = true;

    sb.append("routeResult:");
    if (this.routeResult == null) {
      sb.append("null");
    } else {
      sb.append(this.routeResult);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("payload:");
    if (this.payload == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.payload, sb);
    }
    first = false;
    if (isSetMessage()) {
      if (!first) sb.append(", ");
      sb.append("message:");
      if (this.message == null) {
        sb.append("null");
      } else {
        sb.append(this.message);
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TCommandTransferResponseStandardSchemeFactory implements SchemeFactory {
    public TCommandTransferResponseStandardScheme getScheme() {
      return new TCommandTransferResponseStandardScheme();
    }
  }

  private static class TCommandTransferResponseStandardScheme extends StandardScheme<TCommandTransferResponse> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCommandTransferResponse struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROUTE_RESULT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.routeResult = TRouteResult.findByValue(iprot.readI32());
              struct.setRouteResultIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PAYLOAD
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.payload = iprot.readBinary();
              struct.setPayloadIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // MESSAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.message = iprot.readString();
              struct.setMessageIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCommandTransferResponse struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.routeResult != null) {
        oprot.writeFieldBegin(ROUTE_RESULT_FIELD_DESC);
        oprot.writeI32(struct.routeResult.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.payload != null) {
        oprot.writeFieldBegin(PAYLOAD_FIELD_DESC);
        oprot.writeBinary(struct.payload);
        oprot.writeFieldEnd();
      }
      if (struct.message != null) {
        if (struct.isSetMessage()) {
          oprot.writeFieldBegin(MESSAGE_FIELD_DESC);
          oprot.writeString(struct.message);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCommandTransferResponseTupleSchemeFactory implements SchemeFactory {
    public TCommandTransferResponseTupleScheme getScheme() {
      return new TCommandTransferResponseTupleScheme();
    }
  }

  private static class TCommandTransferResponseTupleScheme extends TupleScheme<TCommandTransferResponse> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCommandTransferResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRouteResult()) {
        optionals.set(0);
      }
      if (struct.isSetPayload()) {
        optionals.set(1);
      }
      if (struct.isSetMessage()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRouteResult()) {
        oprot.writeI32(struct.routeResult.getValue());
      }
      if (struct.isSetPayload()) {
        oprot.writeBinary(struct.payload);
      }
      if (struct.isSetMessage()) {
        oprot.writeString(struct.message);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCommandTransferResponse struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.routeResult = TRouteResult.findByValue(iprot.readI32());
        struct.setRouteResultIsSet(true);
      }
      if (incoming.get(1)) {
        struct.payload = iprot.readBinary();
        struct.setPayloadIsSet(true);
      }
      if (incoming.get(2)) {
        struct.message = iprot.readString();
        struct.setMessageIsSet(true);
      }
    }
  }

}

