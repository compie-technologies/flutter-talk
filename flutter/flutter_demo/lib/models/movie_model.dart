import 'package:json_annotation/json_annotation.dart';

part 'movie_model.g.dart';

@JsonSerializable(nullable: false)
class MovieModel {

  @JsonKey(name: "poster_path", required: false, nullable: true,)
  final String posterPath;

  @JsonKey(name: "adult", required: false, nullable: true,)
  final bool adult;

  @JsonKey(name: "overview", required: false, nullable: true,)
  final String overview;

  @JsonKey(name: "release_date", required: false, nullable: true,)
  final DateTime releaseDate;

  @JsonKey(name: "genre_ids", required: false, nullable: true,)
  final List<int> genreIds;

  @JsonKey(name: "original_title", required: false, nullable: true,)
  final String originalTitle;

  @JsonKey(name: "original_langauge", required: false, nullable: true,)
  final String originalLanguage;

  @JsonKey(name: "title", required: false, nullable: true,)
  final String title;

  @JsonKey(name: "backdrop_path", required: false, nullable: true,)
  final String backdropPath;

  @JsonKey(name: "popularity", required: false, nullable: true,)
  final double popularity;

  @JsonKey(name: "vote_count", required: false, nullable: true,)
  final int voteCount;

  @JsonKey(name: "video", required: false, nullable: true,)
  final bool video;

  @JsonKey(name: "vote_average", required: false, nullable: true,)
  final double voteAverage;

  MovieModel({this.posterPath, this.adult, this.overview, this.releaseDate, this.genreIds, this.originalTitle, this.originalLanguage, this.title, this.backdropPath, this.popularity, this.voteCount, this.video, this.voteAverage});

  factory MovieModel.fromJson(Map<String, dynamic> json) =>
      _$MovieModelFromJson(json);

  Map<String, dynamic> toJson() => _$MovieModelToJson(this);
}