package org.smartregister.uniceftunisia.reporting.monthly.sent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import org.smartregister.uniceftunisia.R
import org.smartregister.uniceftunisia.domain.MonthlyTally
import org.smartregister.uniceftunisia.reporting.ReportsDao.dateFormatter
import org.smartregister.uniceftunisia.reporting.convertToNamedMonth
import org.smartregister.uniceftunisia.reporting.translateString

class SentReportsExpandableListAdapter() : BaseExpandableListAdapter() {

    var sentReportYearHeaders: List<String> = arrayListOf()

    var sentReports: Map<String, List<MonthlyTally>> = hashMapOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getGroupCount() = sentReportYearHeaders.size

    override fun getChildrenCount(groupPosition: Int) = sentReports.getValue(getGroup(groupPosition)).size

    override fun getGroup(groupPosition: Int) = sentReportYearHeaders[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int) =
            sentReports.getValue(getGroup(groupPosition))[childPosition]

    override fun getGroupId(groupPosition: Int) = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun hasStableIds() = false

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View =
            convertView
                    ?: LayoutInflater.from(parent.context).inflate(R.layout.sent_monthly_report_header_item,
                            parent, false).apply {
                        val group = getGroup(groupPosition)
                        findViewById<TextView>(R.id.sentReportGroupHeaderTextView).text = group
                        tag = group
                    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View =
            convertView
                    ?: LayoutInflater.from(parent.context).inflate(R.layout.sent_monthly_report_item,
                            parent, false).apply {
                        with(getChild(groupPosition, childPosition)) {
                            tag = this
                            val translatedYearMonth = dateFormatter("yyyy-MM")
                                    .format(month)
                                    .convertToNamedMonth(hasHyphen = true).translateString(context)
                            findViewById<TextView>(R.id.dateReportSentTextView).text = translatedYearMonth
                            val sentReportDetails = context.getString(R.string.sent_report_details,
                                    dateFormatter("dd/MM/YYYY").format(dateSent), providerId)
                            findViewById<TextView>(R.id.sentReportDetailsTextView).text = sentReportDetails
                        }
                    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true
}