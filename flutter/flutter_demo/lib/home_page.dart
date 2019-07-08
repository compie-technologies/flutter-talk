import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter_demo/api.dart';
import 'package:flutter_demo/consts.dart';
import 'package:flutter_demo/models/base_network_response.dart';
import 'package:flutter_demo/models/error_model.dart';
import 'package:flutter_demo/models/genre.dart';
import 'package:flutter_demo/models/movie_model.dart';
import 'package:flutter_demo/models/movies_response.dart';
import 'package:flutter_demo/snackbar_manager.dart';

class HomePage extends StatefulWidget {
  HomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  ScrollController _scrollController;

  bool isRefreshing = false;
  bool isFetchingMore = false;

  bool shouldUpdateOnBottomReach = true;

  List<Genre> genres = [];

  List<MovieModel> movies = [];

  List<String> errors = [];

  int nextPage = 1;

  @override
  void initState() {
    _scrollController = ScrollController();
    _scrollController.addListener(_scrollListener);

    fetchGenres();
    fetchMovies(nextPage);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    ///showcase hot reload by changing this value and saving.
    bool isGrid = false;

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: SnackBarManager(
        clearDisplayedMessage: clearDisplayedMessage,
        errors: errors,
        child: movies.length != 0 ? RefreshIndicator(
            child: isGrid
                ? GridView.builder(
              controller: _scrollController,
              physics: AlwaysScrollableScrollPhysics(),
              gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: 0.66,
              ),
              itemBuilder: (BuildContext context, index) {
                return drawGridItem(movies[index].posterPath ?? '',
                  movies[index].title, getGenreString(movies[index].genreIds, 2),);
              },
              itemCount: movies.length,
            )
                : ListView.builder(
              controller: _scrollController,
              physics: AlwaysScrollableScrollPhysics(),
              itemBuilder: (BuildContext context, index) {
                return drawListItem(movies[index].posterPath ?? '',
                  movies[index].title, getGenreString(movies[index].genreIds, 4),);
              },
              itemCount: movies.length,
            ),
            onRefresh: () async {
              print('onRefresh called');
              refreshMovies();
              return getRefreshFuture();
            }) : Container(),
      ),
    );
  }

  Widget drawGridItem(String url, String title, String genres) {
    return Container(
      child: Stack(
        children: <Widget>[
          Positioned(
            top: 0,
            bottom: 0,
            right: 0,
            left: 0,
            child: FadeInImage(
                placeholder: AssetImage('assets/images/placeholder.jpeg'),
                image: NetworkImage(baseImageUrl + url),
                fit: BoxFit.cover,
            ),
          ),
          Positioned(
            bottom: 0,
            left: 0,
            right: 0,
            child: Container(
              padding: EdgeInsets.all(10),
              color: Colors.black.withOpacity(0.5),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(title,
                      style: TextStyle(fontSize: 20, color: Colors.white)),
                  SizedBox(
                    height: 5,
                  ),
                  Text(genres,
                      style: TextStyle(fontSize: 14, color: Colors.grey, fontWeight: FontWeight.w900)),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }

  Widget drawListItem(String url, String title, String genres) {
    return Container(
      height: 100,
      color: Colors.black,
      child: Row(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          FadeInImage(
            placeholder: AssetImage('assets/images/placeholder.jpeg'),
            image: NetworkImage(baseImageUrl + url),
            fit: BoxFit.contain,
            height: 90,
            width: 60,
          ),
          SizedBox(
            width: 20,
          ),
          Expanded(child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: <Widget>[
              Container(child: Text(title,
                  overflow: TextOverflow.fade,
                  style: TextStyle(fontSize: 20, color: Colors.white)),),
              SizedBox(
                height: 5,
              ),
              Text(genres,
                  style: TextStyle(fontSize: 12, color: Colors.grey)),
            ],
          ),),
        ],
      ),
    );
  }

  String getGenreString(List<int> genreIds, int limit) {
    String result = '';
    for (int i = 0; i < min(limit, genreIds.length); i++) {
      Genre genre = genres.firstWhere((genre) {return genreIds[i] == genre.id;}, orElse: () {});
      if (genre != null) {
        if (result.length != 0) {
          result += ', ';
        }
        result += genre.name;
      }
    }
    return result;
  }

  void clearDisplayedMessage(String errorToRemove) async {
    setState(() {
      errors.removeWhere((error) {return error == errorToRemove;});
    });
  }

  void refreshMovies() async {
    setState(() {
      isRefreshing = true;
    });
    await fetchMovies(1);
    setState(() {
      isRefreshing = false;
    });
  }

  void fetchNext() async {
    setState(() {
      isFetchingMore = true;
    });
    await fetchMovies(nextPage);
    setState(() {
      isFetchingMore = false;
    });
  }

  Future fetchMovies(int pageToFetch) async {
    /// we use var here to allow the compiler to infer the type from the api method.
    var response = await getMovies(pageToFetch);
    if (response.error != null) {
      handleError(response.error);
      return;
    }

    setState(() {
      if (response.response.page == 1) {
        movies = response.response.results;
      } else {
        movies.addAll(response.response.results);
      }

      if (response.response.page < response.response.totalPages) {
        nextPage = response.response.page + 1;
      } else {
        nextPage = null;
      }
    });
  }

  void fetchGenres() async {
    /// we use var here to allow the compiler to infer the type from the api method.
    var response = await getGenres();
    if (response.error != null) {
      handleError(response.error);
      return;
    }

    setState(() {
      genres = response.response.genres;
    });
  }

  void handleError(ErrorModel errorModel) {
    if (errorModel.statusMessage != null && errorModel.statusMessage != '') {
      setState(() {
        errors.add(errorModel.statusMessage);
      });
    }
    if (errorModel.errors != null && errorModel.errors.length != 0) {

      List<String> errorsToAdd = [];

      for (String error in errorModel.errors) {
        errorsToAdd.add(error);
      }

      setState(() {
        errors.addAll(errorsToAdd);
      });
    }
  }

  Future getRefreshFuture() {
    return new Future.delayed(Duration(milliseconds: 100), () {
      if (isRefreshing) {
        return getRefreshFuture();
      } else {
        return Future.delayed(Duration(seconds: 0));
      }
    });
  }

  _scrollListener() {
    if (_scrollController.offset >=
        _scrollController.position.maxScrollExtent - 100.0 &&
        !_scrollController.position.outOfRange) {
      if (shouldUpdateOnBottomReach) {
        shouldUpdateOnBottomReach = false;

        if (!isFetchingMore && nextPage != null) {
          fetchNext();
        }
      }
    } else {
      shouldUpdateOnBottomReach = true;
    }
  }
}
