import 'package:json_annotation/json_annotation.dart';

part 'genre.g.dart';

@JsonSerializable(nullable: false)
class Genre {

  @JsonKey(name: "id", required: false, nullable: true,)
  final int id;

  @JsonKey(name: "name", required: false, nullable: true,)
  final String name;

  Genre({this.id, this.name});

  factory Genre.fromJson(Map<String, dynamic> json) => _$GenreFromJson(json);

  Map<String, dynamic> toJson() => _$GenreToJson(this);
}