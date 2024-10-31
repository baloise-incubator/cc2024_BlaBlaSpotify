/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 3.2.1263 on 2024-10-31 11:44:40.

export interface ProgramList {
    channel: string;
    businessUnit: string;
    programs: Program[];
}

export interface DeviceList {
    devices: Device[];
}

export interface Play {
    context_uri: string;
    play: boolean;
}

export interface PlaylistList {
    href: string;
    limit: number;
    next: string;
    offset: number;
    previous: string;
    total: number;
    items: Item[];
}

export interface User {
    country: string;
    email: string;
    followers: Followers;
    href: string;
    id: string;
    images: Image[];
    product: string;
    type: string;
    uri: string;
    display_name: string;
    explicit_content: ExplicitContent;
    external_urls: ExternalUrls;
}

export interface Program {
    title: string;
    shortDescription: string;
    dateTimes: ProgramDateTimes;
}

export interface Device {
    id: string;
    name: string;
    type: string;
    volumePercent: number;
    supportsVolume: boolean;
    active: boolean;
    restricted: boolean;
    privateSession: boolean;
}

export interface Item {
    collaborative: boolean;
    description: string;
    href: string;
    id: string;
    name: string;
    owner: Owner;
    tracks: Tracks;
    type: string;
    uri: string;
    public: boolean;
    snapshot_id: string;
}

export interface Followers {
    href: string;
    total: number;
}

export interface Image {
    url: string;
    height: number;
    width: number;
}

export interface ExplicitContent {
    filter_enabled: boolean;
    filter_locked: boolean;
}

export interface ExternalUrls {
    spotify: string;
}

export interface ProgramDateTimes {
    startTime: string;
    endTime: string;
    duration: string;
    timezone: string;
}

export interface Owner {
    href: string;
    id: string;
    type: string;
    uri: string;
    display_name: string;
}

export interface Tracks {
    href: string;
    total: number;
}
