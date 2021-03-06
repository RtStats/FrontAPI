/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.github.rtstats.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TParamAddSet implements org.apache.thrift.TBase<TParamAddSet, TParamAddSet._Fields>, java.io.Serializable, Cloneable, Comparable<TParamAddSet> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TParamAddSet");

  private static final org.apache.thrift.protocol.TField COUNTER_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("counterName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField VALUE_FIELD_DESC = new org.apache.thrift.protocol.TField("value", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_MILLISEC_FIELD_DESC = new org.apache.thrift.protocol.TField("timestampMillisec", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TParamAddSetStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TParamAddSetTupleSchemeFactory());
  }

  public String counterName; // required
  public long value; // optional
  public long timestampMillisec; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COUNTER_NAME((short)1, "counterName"),
    VALUE((short)2, "value"),
    TIMESTAMP_MILLISEC((short)3, "timestampMillisec");

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
        case 1: // COUNTER_NAME
          return COUNTER_NAME;
        case 2: // VALUE
          return VALUE;
        case 3: // TIMESTAMP_MILLISEC
          return TIMESTAMP_MILLISEC;
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
  private static final int __VALUE_ISSET_ID = 0;
  private static final int __TIMESTAMPMILLISEC_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.VALUE,_Fields.TIMESTAMP_MILLISEC};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COUNTER_NAME, new org.apache.thrift.meta_data.FieldMetaData("counterName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VALUE, new org.apache.thrift.meta_data.FieldMetaData("value", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TIMESTAMP_MILLISEC, new org.apache.thrift.meta_data.FieldMetaData("timestampMillisec", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TParamAddSet.class, metaDataMap);
  }

  public TParamAddSet() {
    this.value = 1L;

    this.timestampMillisec = 0L;

  }

  public TParamAddSet(
    String counterName)
  {
    this();
    this.counterName = counterName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TParamAddSet(TParamAddSet other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCounterName()) {
      this.counterName = other.counterName;
    }
    this.value = other.value;
    this.timestampMillisec = other.timestampMillisec;
  }

  public TParamAddSet deepCopy() {
    return new TParamAddSet(this);
  }

  @Override
  public void clear() {
    this.counterName = null;
    this.value = 1L;

    this.timestampMillisec = 0L;

  }

  public String getCounterName() {
    return this.counterName;
  }

  public TParamAddSet setCounterName(String counterName) {
    this.counterName = counterName;
    return this;
  }

  public void unsetCounterName() {
    this.counterName = null;
  }

  /** Returns true if field counterName is set (has been assigned a value) and false otherwise */
  public boolean isSetCounterName() {
    return this.counterName != null;
  }

  public void setCounterNameIsSet(boolean value) {
    if (!value) {
      this.counterName = null;
    }
  }

  public long getValue() {
    return this.value;
  }

  public TParamAddSet setValue(long value) {
    this.value = value;
    setValueIsSet(true);
    return this;
  }

  public void unsetValue() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __VALUE_ISSET_ID);
  }

  /** Returns true if field value is set (has been assigned a value) and false otherwise */
  public boolean isSetValue() {
    return EncodingUtils.testBit(__isset_bitfield, __VALUE_ISSET_ID);
  }

  public void setValueIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __VALUE_ISSET_ID, value);
  }

  public long getTimestampMillisec() {
    return this.timestampMillisec;
  }

  public TParamAddSet setTimestampMillisec(long timestampMillisec) {
    this.timestampMillisec = timestampMillisec;
    setTimestampMillisecIsSet(true);
    return this;
  }

  public void unsetTimestampMillisec() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMPMILLISEC_ISSET_ID);
  }

  /** Returns true if field timestampMillisec is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestampMillisec() {
    return EncodingUtils.testBit(__isset_bitfield, __TIMESTAMPMILLISEC_ISSET_ID);
  }

  public void setTimestampMillisecIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIMESTAMPMILLISEC_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case COUNTER_NAME:
      if (value == null) {
        unsetCounterName();
      } else {
        setCounterName((String)value);
      }
      break;

    case VALUE:
      if (value == null) {
        unsetValue();
      } else {
        setValue((Long)value);
      }
      break;

    case TIMESTAMP_MILLISEC:
      if (value == null) {
        unsetTimestampMillisec();
      } else {
        setTimestampMillisec((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case COUNTER_NAME:
      return getCounterName();

    case VALUE:
      return Long.valueOf(getValue());

    case TIMESTAMP_MILLISEC:
      return Long.valueOf(getTimestampMillisec());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case COUNTER_NAME:
      return isSetCounterName();
    case VALUE:
      return isSetValue();
    case TIMESTAMP_MILLISEC:
      return isSetTimestampMillisec();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TParamAddSet)
      return this.equals((TParamAddSet)that);
    return false;
  }

  public boolean equals(TParamAddSet that) {
    if (that == null)
      return false;

    boolean this_present_counterName = true && this.isSetCounterName();
    boolean that_present_counterName = true && that.isSetCounterName();
    if (this_present_counterName || that_present_counterName) {
      if (!(this_present_counterName && that_present_counterName))
        return false;
      if (!this.counterName.equals(that.counterName))
        return false;
    }

    boolean this_present_value = true && this.isSetValue();
    boolean that_present_value = true && that.isSetValue();
    if (this_present_value || that_present_value) {
      if (!(this_present_value && that_present_value))
        return false;
      if (this.value != that.value)
        return false;
    }

    boolean this_present_timestampMillisec = true && this.isSetTimestampMillisec();
    boolean that_present_timestampMillisec = true && that.isSetTimestampMillisec();
    if (this_present_timestampMillisec || that_present_timestampMillisec) {
      if (!(this_present_timestampMillisec && that_present_timestampMillisec))
        return false;
      if (this.timestampMillisec != that.timestampMillisec)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(TParamAddSet other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetCounterName()).compareTo(other.isSetCounterName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCounterName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.counterName, other.counterName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetValue()).compareTo(other.isSetValue());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValue()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.value, other.value);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimestampMillisec()).compareTo(other.isSetTimestampMillisec());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestampMillisec()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestampMillisec, other.timestampMillisec);
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
    StringBuilder sb = new StringBuilder("TParamAddSet(");
    boolean first = true;

    sb.append("counterName:");
    if (this.counterName == null) {
      sb.append("null");
    } else {
      sb.append(this.counterName);
    }
    first = false;
    if (isSetValue()) {
      if (!first) sb.append(", ");
      sb.append("value:");
      sb.append(this.value);
      first = false;
    }
    if (isSetTimestampMillisec()) {
      if (!first) sb.append(", ");
      sb.append("timestampMillisec:");
      sb.append(this.timestampMillisec);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (counterName == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'counterName' was not present! Struct: " + toString());
    }
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

  private static class TParamAddSetStandardSchemeFactory implements SchemeFactory {
    public TParamAddSetStandardScheme getScheme() {
      return new TParamAddSetStandardScheme();
    }
  }

  private static class TParamAddSetStandardScheme extends StandardScheme<TParamAddSet> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TParamAddSet struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COUNTER_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.counterName = iprot.readString();
              struct.setCounterNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VALUE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.value = iprot.readI64();
              struct.setValueIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TIMESTAMP_MILLISEC
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestampMillisec = iprot.readI64();
              struct.setTimestampMillisecIsSet(true);
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

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TParamAddSet struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.counterName != null) {
        oprot.writeFieldBegin(COUNTER_NAME_FIELD_DESC);
        oprot.writeString(struct.counterName);
        oprot.writeFieldEnd();
      }
      if (struct.isSetValue()) {
        oprot.writeFieldBegin(VALUE_FIELD_DESC);
        oprot.writeI64(struct.value);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTimestampMillisec()) {
        oprot.writeFieldBegin(TIMESTAMP_MILLISEC_FIELD_DESC);
        oprot.writeI64(struct.timestampMillisec);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TParamAddSetTupleSchemeFactory implements SchemeFactory {
    public TParamAddSetTupleScheme getScheme() {
      return new TParamAddSetTupleScheme();
    }
  }

  private static class TParamAddSetTupleScheme extends TupleScheme<TParamAddSet> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TParamAddSet struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.counterName);
      BitSet optionals = new BitSet();
      if (struct.isSetValue()) {
        optionals.set(0);
      }
      if (struct.isSetTimestampMillisec()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetValue()) {
        oprot.writeI64(struct.value);
      }
      if (struct.isSetTimestampMillisec()) {
        oprot.writeI64(struct.timestampMillisec);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TParamAddSet struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.counterName = iprot.readString();
      struct.setCounterNameIsSet(true);
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.value = iprot.readI64();
        struct.setValueIsSet(true);
      }
      if (incoming.get(1)) {
        struct.timestampMillisec = iprot.readI64();
        struct.setTimestampMillisecIsSet(true);
      }
    }
  }

}

