/*
 * MIT License
 *
 * Copyright (c) 2020 atlarge-research
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.opendc.compute.core.virt.service

/**
 * An event that is emitted by the [VirtProvisioningService].
 */
public sealed class VirtProvisioningEvent {
    /**
     * The service that has emitted the event.
     */
    public abstract val provisioner: VirtProvisioningService

    /**
     * An event emitted for writing metrics.
     */
    public data class MetricsAvailable(
        override val provisioner: VirtProvisioningService,
        public val totalHostCount: Int,
        public val availableHostCount: Int,
        public val totalVmCount: Int,
        public val activeVmCount: Int,
        public val inactiveVmCount: Int,
        public val waitingVmCount: Int,
        public val failedVmCount: Int
    ) : VirtProvisioningEvent()
}
