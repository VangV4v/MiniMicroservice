// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HelloService.proto
// Protobuf Java Version: 4.26.0

package com.vang.customerservice.query;

public final class HelloServiceOuterClass {
  private HelloServiceOuterClass() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 0,
      /* suffix= */ "",
      HelloServiceOuterClass.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_vang_customerservice_query_HelloRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_vang_customerservice_query_HelloRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_com_vang_customerservice_query_HelloResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_vang_customerservice_query_HelloResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022HelloService.proto\022\036com.vang.customers" +
      "ervice.query\"\033\n\014HelloRequest\022\013\n\003jwt\030\001 \001(" +
      "\t\"\036\n\rHelloResponse\022\r\n\005token\030\001 \001(\t2t\n\014Hel" +
      "loService\022d\n\005hello\022,.com.vang.customerse" +
      "rvice.query.HelloRequest\032-.com.vang.cust" +
      "omerservice.query.HelloResponseB\002P\001b\006pro" +
      "to3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_com_vang_customerservice_query_HelloRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_com_vang_customerservice_query_HelloRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_vang_customerservice_query_HelloRequest_descriptor,
        new java.lang.String[] { "Jwt", });
    internal_static_com_vang_customerservice_query_HelloResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_com_vang_customerservice_query_HelloResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_com_vang_customerservice_query_HelloResponse_descriptor,
        new java.lang.String[] { "Token", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
