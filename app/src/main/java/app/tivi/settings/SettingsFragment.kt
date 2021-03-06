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

package app.tivi.settings

import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import app.tivi.BuildConfig
import app.tivi.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        findPreference<Preference>("privacy_policy")?.setOnPreferenceClickListener {
            CustomTabsIntent.Builder()
                    .setToolbarColor(requireContext().getColor(R.color.colorPrimaryDark))
                    .build()
                    .launchUrl(requireContext(), getString(R.string.privacy_policy_url).toUri())
            true
        }

        findPreference<Preference>("version")?.apply {
            summary = getString(R.string.settings_app_version_summary,
                    BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE)
        }
    }
}
