function getPlayer() {
    return document.getElementById("player");
}

function setPlayerStatus(newStatus) {
    document.getElementById("play-button")
    .getElementsByTagName("button")[0]
    .getElementsByTagName("span")[0]
    .innerText = newStatus
}

function togglePlayback() {
    const player = getPlayer()

    if (player.paused) {
        startPlayback()
    } else {
        stopPlayback()
    }
}

function startPlayback() {
    const player = getPlayer();
    player.load();
    player.play();
}

function stopPlayback() {
    getPlayer().pause();
}