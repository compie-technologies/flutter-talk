import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';

class SnackBarManager extends StatelessWidget {

  final Widget child;

  final List<String> errors;

  final Function clearDisplayedMessage;

  SnackBarManager({Key key, this.child, this.errors, this.clearDisplayedMessage}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (errors != null && errors.length > 0) {
      String error = errors[0];

      final snackBar = SnackBar(
        backgroundColor: Colors.black,
        content: new Row(
          children: <Widget>[
            new Expanded(
              child: Text(
                error,
                softWrap: true,
                style: TextStyle(
                  fontSize: 16,
                  color: Colors.white,
                  fontWeight: FontWeight.normal,
                ),
              ),
            ),
          ],
        ),
      );

      /// we can't update the UI during the build sequence, so we use the
      /// SchedulerBinding addPostFrameCallback to wait for the sequence to
      /// end.
      SchedulerBinding.instance.addPostFrameCallback((_) => Scaffold.of(context).showSnackBar(snackBar));

      if (clearDisplayedMessage != null) {
        clearDisplayedMessage(error);
      }

    }
    return new Container(
      child: child,
    );
  }

}