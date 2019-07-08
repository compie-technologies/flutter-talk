import 'package:flutter_demo/models/error_model.dart';

class BaseNetworkResponse<T> {
  ErrorModel error;

  T response;

  BaseNetworkResponse({this.error, this.response});
}
