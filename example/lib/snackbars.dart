import 'package:flutter/material.dart';

enum SnackBarStatus { success, error }

class SnackBars {
  SnackBars._();

  static void show({
    required BuildContext context,
    required SnackBarStatus status,
    required String message,
  }) {
    Color backgroundColor;

    switch (status) {
      case SnackBarStatus.success:
        backgroundColor = Colors.green;
        break;
      case SnackBarStatus.error:
        backgroundColor = Colors.red;
        break;
    }

    ScaffoldMessenger.of(context).removeCurrentSnackBar();

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        elevation: 0,
        margin: const EdgeInsets.all(24),
        behavior: SnackBarBehavior.floating,
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(10)),
        backgroundColor: backgroundColor,
        content: Center(
          child: Text(
            message,
            style: const TextStyle(
              fontWeight: FontWeight.w500,
            ),
          ),
        ),
      ),
    );
  }
}
