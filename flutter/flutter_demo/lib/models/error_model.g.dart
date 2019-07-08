// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'error_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ErrorModel _$ErrorModelFromJson(Map<String, dynamic> json) {
  return ErrorModel(
      statusMessage: json['status_message'] as String,
      statusCode: json['status_code'] as int,
      errors: (json['errors'] as List)?.map((e) => e as String)?.toList());
}

Map<String, dynamic> _$ErrorModelToJson(ErrorModel instance) =>
    <String, dynamic>{
      'status_message': instance.statusMessage,
      'status_code': instance.statusCode,
      'errors': instance.errors
    };
