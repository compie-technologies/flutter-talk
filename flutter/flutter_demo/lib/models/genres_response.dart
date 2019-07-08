import 'package:flutter_demo/models/genre.dart';
import 'package:json_annotation/json_annotation.dart';

part 'genres_response.g.dart';

@JsonSerializable(nullable: false)
class GenresResponse {

  @JsonKey(name: "genres", required: false, nullable: true,)
  final List<Genre> genres;

  GenresResponse({this.genres});

  factory GenresResponse.fromJson(Map<String, dynamic> json) =>
      _$GenresResponseFromJson(json);

  Map<String, dynamic> toJson() => _$GenresResponseToJson(this);
}