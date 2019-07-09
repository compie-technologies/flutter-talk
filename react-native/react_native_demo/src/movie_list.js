import React, {Component} from 'react';
import {FlatList, View} from "react-native";
import {constants} from "./consts";
import GridItem from "./grid_item";
import {getGenres, getMovies} from "./api";

class MovieList extends Component {
    constructor(props) {
        super(props);

        this.getGenreString = this.getGenreString.bind(this);
        this.fetchGenres = this.fetchGenres.bind(this);
        this.fetchMovies = this.fetchMovies.bind(this);
        this.refreshMovies = this.refreshMovies.bind(this);
        this.fetchMoreMovies = this.fetchMoreMovies.bind(this);

        this.state = {
            genres: [],
            movies: [],
            nextPage: 1,
            isRefreshing: false,
        };

        this.fetchGenres();
        this.fetchMovies(1);

    }

    render() {
        return (
            <FlatList
                data={this.state.movies}
                renderItem={({item}) => (
                    <GridItem title={item.title} genres={this.getGenreString(item.genre_ids, 2)} imageUrl={constants.baseImageUrl + item.poster_path || ''} />
                )}
                numColumns={2}
                keyExtractor={(item, index) => "" + index + "_" + item.id}
                ListFooterComponent={() => <View style={{height: 100}} />}
                refreshing={this.state.isRefreshing}
                onRefresh={this.refreshMovies}
                onEndReached={this.fetchMoreMovies}

            />
        );
    }

    getGenreString(genreIds, limit) {
        let result = '';
        for (let i = 0; i < Math.min(limit, genreIds.length); i++) {
            let genre = this.state.genres.find((genre) => genre.id === genreIds[i]);
            if (genre) {
                if (result.length !== 0) {
                    result += ', ';
                }
                result += genre.name;
            }
        }
        return result;
    }

    refreshMovies() {
        this.fetchMovies(1);
    }

    fetchMoreMovies() {
        if (this.state.nextPage !== null) {
            this.fetchMovies(this.state.nextPage);
        }
    }

    fetchMovies(pageToFetch, isRefresh) {
        if (isRefresh) {
            this.setState({isRefreshing: true});
        }
        getMovies(pageToFetch)
            .then((response) => response.json())
            .then(
                (response) => {
                    console.log('MovieList fetchMovies success', response);
                    this.setState({
                        movies: response.page === 1 ? response.results : this.state.movies.concat(response.results),
                        nextPage: response.page < response.total_pages ? response.page + 1 : null,
                    })
                }
            )
            .catch(
                (errorResponse) => {
                    console.log('MovieList fetchMovies error', errorResponse);
                }
            ).finally(() => {
                if (isRefresh) {
                    this.setState({isRefreshing: false});
                }
        });
    }

    fetchGenres() {
        getGenres()
            .then((response) => response.json())
            .then(
                (response) => {
                    console.log('MovieList fetchGenres success', response);
                    this.setState({
                        genres: response.genres,
                    })
                }
            )
            .catch(
                (errorResponse) => {
                    console.log('MovieList fetchGenres error', errorResponse);
                }
            );
    }

}

export default MovieList;