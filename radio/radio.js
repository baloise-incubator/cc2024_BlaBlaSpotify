function getPlayer() {
    return document.getElementById("player");
}

function setPlayerStatus(newStatus) {
    document.getElementById("status")
        .innerText = newStatus;
}

function startPlayback() {
    const player = getPlayer();
    player.load();
    player.play();
}

function stopPlayback() {
    getPlayer().pause();
}