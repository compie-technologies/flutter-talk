import 'package:json_annotation/json_annotation.dart';

part 'error_model.g.dart';

@JsonSerializable(nullable: false)
class ErrorModel {

  @JsonKey(name: "status_message", required: false, nullable: true,)
  final String statusMessage;

  @JsonKey(name: "status_code", required: false, nullable: true,)
  final int statusCode;

  @JsonKey(name: "errors", required: false, nullable: true,)
  final List<String> errors;

  ErrorModel({this.statusMessage, this.statusCode, this.errors});

  factory ErrorModel.fromJson(Map<String, dynamic> json) =>
      _$ErrorModelFromJson(json);

  Map<String, dynamic> toJson() => _$ErrorModelToJson(this);
}