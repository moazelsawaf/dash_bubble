import 'dart:developer';

import 'package:dash_bubble/dash_bubble.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'snackbars.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    const primaryColor = Color(0xFF04599c);
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Dash Bubble Playground',
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSwatch().copyWith(
          background: primaryColor,
        ),
        appBarTheme: const AppBarTheme(
          backgroundColor: Colors.transparent,
          elevation: 0,
          centerTitle: true,
          titleTextStyle: TextStyle(
            color: Colors.white,
            fontSize: 20,
            fontWeight: FontWeight.w500,
          ),
        ),
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            elevation: 0,
            backgroundColor: Colors.white,
            foregroundColor: primaryColor,
            minimumSize: const Size.fromHeight(50),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(10),
            ),
            textStyle: const TextStyle(
              fontSize: 16,
              fontWeight: FontWeight.w500,
            ),
          ),
        ),
      ),
      home: const HomeScreen(),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        title: const Text('Dash Bubble Playground'),
        centerTitle: true,
      ),
      body: Padding(
        padding: const EdgeInsets.all(24),
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              ElevatedButton(
                onPressed: () => _requestPermission(context),
                child: const Text('Request Permission'),
              ),
              const SizedBox(height: 24),
              ElevatedButton(
                onPressed: () => _hasPermission(context),
                child: const Text('Has Permission?'),
              ),
              const SizedBox(height: 24),
              ElevatedButton(
                onPressed: () => _isRunning(context),
                child: const Text('Is Running?'),
              ),
              const SizedBox(height: 24),
              ElevatedButton(
                onPressed: () {
                  _startBubble(
                    context,
                    options: BubbleOptions(
                      notificationTitle: 'Dash Bubble Playground',
                      notificationText: 'Dash Bubble service is running',
                      bubbleIcon: 'youtube_icon',
                      closeIcon: 'youtube_icon',
                      bubbleSize: 60,
                      enableAnimateToEdge: true,
                      enableBottomShadow: true,
                      enableClose: true,
                      startLocationX: 0,
                      startLocationY: 200,
                      keepAliveWhenAppExit: false,
                    ),
                    onBubbleTap: () {
                      if (kDebugMode) print('Bubble Tapped');

                      SnackBars.show(
                        context: context,
                        status: SnackBarStatus.success,
                        message: 'Bubble Tapped',
                      );
                    },
                  );
                },
                child: const Text('Start Bubble'),
              ),
              const SizedBox(height: 24),
              ElevatedButton(
                onPressed: () => _stopBubble(context),
                child: const Text('Stop Bubble'),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Future<void> _runMethod(
    BuildContext context,
    Future<void> Function() method,
  ) async {
    try {
      await method();
    } catch (error) {
      log(
        name: 'Dash Bubble Playground',
        error.toString(),
      );

      SnackBars.show(
        context: context,
        status: SnackBarStatus.error,
        message: 'Error: ${error.runtimeType}',
      );
    }
  }

  Future<void> _requestPermission(BuildContext context) async {
    await _runMethod(
      context,
      () async {
        final isGranted = await DashBubble.instance.requestPermission();

        SnackBars.show(
          context: context,
          status: SnackBarStatus.success,
          message:
              isGranted ? 'Permission Granted' : 'Permission is not Granted',
        );
      },
    );
  }

  Future<void> _hasPermission(BuildContext context) async {
    await _runMethod(
      context,
      () async {
        final hasPermission = await DashBubble.instance.hasPermission();

        SnackBars.show(
          context: context,
          status: SnackBarStatus.success,
          message: hasPermission
              ? 'Permission Granted'
              : 'Permission is not Granted',
        );
      },
    );
  }

  Future<void> _isRunning(BuildContext context) async {
    await _runMethod(
      context,
      () async {
        final isRunning = await DashBubble.instance.isRunning();

        SnackBars.show(
          context: context,
          status: SnackBarStatus.success,
          message: isRunning ? 'Bubble is Running' : 'Bubble is not Running',
        );
      },
    );
  }

  Future<void> _startBubble(
    BuildContext context, {
    BubbleOptions? options,
    VoidCallback? onBubbleTap,
  }) async {
    await _runMethod(
      context,
      () async {
        final hasStarted = await DashBubble.instance.startBubble(
          options: options,
          onBubbleTap: onBubbleTap,
        );

        SnackBars.show(
          context: context,
          status: SnackBarStatus.success,
          message: hasStarted ? 'Bubble Started' : 'Bubble has not Started',
        );
      },
    );
  }

  Future<void> _stopBubble(BuildContext context) async {
    await _runMethod(
      context,
      () async {
        final hasStopped = await DashBubble.instance.stopBubble();

        SnackBars.show(
          context: context,
          status: SnackBarStatus.success,
          message: hasStopped ? 'Bubble Stopped' : 'Bubble has not Stopped',
        );
      },
    );
  }
}
