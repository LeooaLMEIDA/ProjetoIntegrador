import 'package:flutter/material.dart';
import 'package:youtube_player_flutter/youtube_player_flutter.dart';

class CustomYoutubePlayer extends StatelessWidget {
  final String videoUrl;

  const CustomYoutubePlayer({super.key, required this.videoUrl});

  @override
  Widget build(BuildContext context) {
    return YoutubePlayer(
      controller: YoutubePlayerController(
        initialVideoId: YoutubePlayer.convertUrlToId(videoUrl) ?? '',
        flags: const YoutubePlayerFlags(
            autoPlay: true,
            mute: true,
            hideControls: true,
            hideThumbnail: true,
            loop: true),
      ),
      showVideoProgressIndicator: true,
      progressIndicatorColor: const Color.fromARGB(250, 1, 30, 62),
    );
  }
}
