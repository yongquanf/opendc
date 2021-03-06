/*
 * Copyright (c) 2020 AtLarge Research
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

package org.opendc.compute.simulator.allocation

import mu.KotlinLogging
import org.opendc.compute.simulator.HypervisorView
import org.opendc.compute.simulator.SimVirtProvisioningService

private val logger = KotlinLogging.logger {}

/**
 * Policy replaying VM-cluster assignment.
 *
 * Within each cluster, the active servers on each node determine which node gets
 * assigned the VM image.
 */
public class ReplayAllocationPolicy(private val vmPlacements: Map<String, String>) : AllocationPolicy {
    override fun invoke(): AllocationPolicy.Logic = object : AllocationPolicy.Logic {
        override fun select(
            hypervisors: Set<HypervisorView>,
            image: SimVirtProvisioningService.ImageView
        ): HypervisorView? {
            val clusterName = vmPlacements[image.name]
                ?: throw IllegalStateException("Could not find placement data in VM placement file for VM ${image.name}")
            val machinesInCluster = hypervisors.filter { it.server.name.contains(clusterName) }

            if (machinesInCluster.isEmpty()) {
                logger.info { "Could not find any machines belonging to cluster $clusterName for image ${image.name}, assigning randomly." }
                return hypervisors.maxByOrNull { it.availableMemory }
            }

            return machinesInCluster.maxByOrNull { it.availableMemory }
                ?: throw IllegalStateException("Cloud not find any machine and could not randomly assign")
        }
    }
}
