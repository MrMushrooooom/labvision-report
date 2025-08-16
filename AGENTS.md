# AGENTS.md - 高校实验室管理平台数据报告项目

## Build/Test/Lint Commands
- No package.json or build system detected - this is a pure HTML/CSS project
- No automated test suite configured
- Manual validation: open HTML files in browser and verify PDF generation compatibility

## Code Style Guidelines

### HTML Structure
- Pure HTML + CSS only (no frameworks allowed)
- Each report = one independent HTML file in `/reports/`
- CSS must be inline or in `<style>` tags within HTML files
- Chinese content using UTF-8 encoding with `lang="zh-CN"`

### CSS Constraints (Java Framework Compatibility - iText7/FreeMarker)
- ❌ NO Flexbox: `display: flex`, `justify-content`, `align-items`, `flex-direction`
- ❌ NO Grid: `display: grid`, `grid-template-columns`
- ✅ USE: `display: block/inline-block`, `text-align`, `margin`, `padding`, `vertical-align`
- ✅ Charts: CSS conic-gradient for pie charts, SVG paths for line charts, inline-block for bar charts

### Design Standards
- Professional design with dark themes (deep blue/purple/green)
- Font family: "Microsoft YaHei", "SimSun", "Arial", sans-serif
- No animations or CSS3 transitions
- PDF-optimized with `@media print` styles and `page-break-after: always`

### File Organization
- `/docs/` - Report structure design documents (markdown)
- `/reports/` - Generated HTML report files  
- Design docs first, then implement HTML reports