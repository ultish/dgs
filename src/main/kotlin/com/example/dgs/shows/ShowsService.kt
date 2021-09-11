package com.example.dgs.shows

import com.example.dgs.generated.types.Show

interface ShowsService {
    fun shows(): List<Show>
}