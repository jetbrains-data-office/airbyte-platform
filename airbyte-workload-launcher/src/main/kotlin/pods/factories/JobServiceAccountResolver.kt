/*
 * Copyright (c) 2020-2026 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.workload.launcher.pods.factories

import io.airbyte.featureflag.FeatureFlagClient
import io.airbyte.featureflag.JobServiceAccountOverride
import io.airbyte.featureflag.Workspace
import java.util.UUID

/**
 * Returns the Kubernetes service account for a job pod.
 *
 * The flag [JobServiceAccountOverride] is read with the workspace as context.
 * If the flag has a non-empty value, that value is the service account.
 * If the flag is empty, [configured] is the service account.
 */
internal fun FeatureFlagClient.resolveJobServiceAccount(
  configured: String?,
  workspaceId: UUID,
): String? = stringVariation(JobServiceAccountOverride, Workspace(workspaceId)).ifEmpty { configured }
