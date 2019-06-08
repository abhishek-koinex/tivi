/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.appinitializers

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.view.Gravity
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.EpoxyController
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper
import javax.inject.Inject

class EpoxyInitializer @Inject constructor() : AppInitializer {
    override fun init() {
        // Make EpoxyController async
        val handlerThread = HandlerThread("epoxy")
        handlerThread.start()

        Handler(handlerThread.looper).also {
            EpoxyController.defaultDiffingHandler = it
            EpoxyController.defaultModelBuildingHandler = it
        }

        // Also setup Carousel to use a more sane snapping behavior
        Carousel.setDefaultGlobalSnapHelperFactory(object : Carousel.SnapHelperFactory() {
            override fun buildSnapHelper(context: Context): SnapHelper {
                return GravitySnapHelper(Gravity.START)
            }
        })
    }
}