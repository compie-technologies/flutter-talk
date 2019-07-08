import 'package:flutter_demo/models/movie_model.dart';
import 'package:json_annotation/json_annotation.dart';

part 'movies_response.g.dart';

@JsonSerializable(nullable: false)
class MoviesResponse {

  @JsonKey(name: "page", required: false, nullable: true,)
  final int page;

  @JsonKey(name: "results", required: false, nullable: true,)
  final List<MovieModel> results;

  @JsonKey(name: "total_results", required: false, nullable: true,)
  final int totalResults;

  @JsonKey(name: "total_pages", required: false, nullable: true,)
  final int totalPages;

  MoviesResponse({this.page, this.results, this.totalResults, this.totalPages});

  factory MoviesResponse.fromJson(Map<String, dynamic> json) =>
      _$MoviesResponseFromJson(json);

  Map<String, dynamic> toJson() => _$MoviesResponseToJson(this);
}