// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'movies_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

MoviesResponse _$MoviesResponseFromJson(Map<String, dynamic> json) {
  return MoviesResponse(
      page: json['page'] as int,
      results: (json['results'] as List)
          ?.map((e) =>
              e == null ? null : MovieModel.fromJson(e as Map<String, dynamic>))
          ?.toList(),
      totalResults: json['total_results'] as int,
      totalPages: json['total_pages'] as int);
}

Map<String, dynamic> _$MoviesResponseToJson(MoviesResponse instance) =>
    <String, dynamic>{
      'page': instance.page,
      'results': instance.results,
      'total_results': instance.totalResults,
      'total_pages': instance.totalPages
    };
